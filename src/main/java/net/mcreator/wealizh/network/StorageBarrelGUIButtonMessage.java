package net.mcreator.wealizh.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.mcreator.wealizh.procedures.GUIRModeButtonClickPProcedure;
import net.mcreator.wealizh.procedures.GUIModeButtonClickPProcedure;
import net.mcreator.wealizh.WealizhMod;

@EventBusSubscriber
public record StorageBarrelGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<StorageBarrelGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WealizhMod.MODID, "storage_barrel_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, StorageBarrelGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, StorageBarrelGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new StorageBarrelGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<StorageBarrelGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final StorageBarrelGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			GUIRModeButtonClickPProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			GUIModeButtonClickPProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		WealizhMod.addNetworkMessage(StorageBarrelGUIButtonMessage.TYPE, StorageBarrelGUIButtonMessage.STREAM_CODEC, StorageBarrelGUIButtonMessage::handleData);
	}
}