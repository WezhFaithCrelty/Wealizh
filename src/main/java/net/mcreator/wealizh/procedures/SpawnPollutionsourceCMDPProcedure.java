package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.wealizh.init.WealizhModItems;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class SpawnPollutionsourceCMDPProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		PollutionSourceSpawnPProcedure.execute(world, commandParameterBlockPos(arguments, "location").getX(), commandParameterBlockPos(arguments, "location").getY(), commandParameterBlockPos(arguments, "location").getZ(),
				DoubleArgumentType.getDouble(arguments, "CoD_num"), DoubleArgumentType.getDouble(arguments, "Rad_num"), DoubleArgumentType.getDouble(arguments, "Tox_num"));
		if (world instanceof ServerLevel _serverLevelGR6 && _serverLevelGR6.getGameRules().getBoolean(GameRules.RULE_SENDCOMMANDFEEDBACK)) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]")
						? "\u5DF2\u5728" + commandParameterBlockPos(arguments, "origin").getX() + " " + commandParameterBlockPos(arguments, "origin").getY() + " " + commandParameterBlockPos(arguments, "origin").getZ()
								+ "\u5904\u91CA\u653E\u4E86\u6C61\u67D3\u6E90"
						: "The pollution source has been released " + commandParameterBlockPos(arguments, "origin").getX() + " " + commandParameterBlockPos(arguments, "origin").getY() + " " + commandParameterBlockPos(arguments, "origin").getZ())),
						false);
		}
	}

	private static BlockPos commandParameterBlockPos(CommandContext<CommandSourceStack> arguments, String parameter) {
		try {
			return BlockPosArgument.getLoadedBlockPos(arguments, parameter);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return new BlockPos(0, 0, 0);
		}
	}
}