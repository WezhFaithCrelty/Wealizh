package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.world.inventory.*;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

@EventBusSubscriber
public class BlockRightClickFYPProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getLevel().getBlockState(event.getPos()), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		ItemStack item_i = ItemStack.EMPTY;
		double item_num = 0;
		if (WealizhModBlocks.ASSEMBLY_TYPE_CONTROL_CONSOLE.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (AssemblyMachineITFProcedure.execute(world, x, y, z, blockstate)) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("AssemblyMachineGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new AssemblyMachineGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
				if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:assembly_assembly_machine"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (WealizhModBlocks.FLINT_BLOCK.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && (WealizhModItems.PEBBLE.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
				|| Items.IRON_NUGGET == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) && entity.isShiftKeyDown()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.LAVA, (x + Mth.nextDouble(RandomSource.create(), 0, 1)), (y + 1), (z + Mth.nextDouble(RandomSource.create(), 0, 1)), Mth.nextInt(RandomSource.create(), 1, 3), 0, 0, 0, 0.5);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x + 0.5, y + 1, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("item.flintandsteel.use")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound((x + 0.5), (y + 1), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("item.flintandsteel.use")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (Mth.nextInt(RandomSource.create(), 1, 18) == 1 && world.isEmptyBlock(BlockPos.containing(x, y + 1, z))) {
				world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.FIRE.defaultBlockState(), 3);
				if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:the_fire_from_the_flint_is_shining"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
		if (WealizhModBlocks.KILN.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
				&& entity.isShiftKeyDown()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (getPropertyByName(blockstate, "open") instanceof BooleanProperty _getbp34 && blockstate.getValue(_getbp34)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.iron_trapdoor.close")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.iron_trapdoor.close")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			} else {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.iron_trapdoor.open")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.iron_trapdoor.open")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
		if (Blocks.END_PORTAL_FRAME == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (getPropertyByName(blockstate, "eye") instanceof BooleanProperty _getbp45 && blockstate.getValue(_getbp45)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("eye") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				if (!(entity instanceof Player _plr47 && _plr47.gameMode() == GameType.CREATIVE)) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(Items.ENDER_EYE).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
			}
		}
		if (WealizhModBlocks.PRESSING_MACHINE.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (WealizhModBlocks.PRESSING_STORAGE_ROOM.get() == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y - 1, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("PressingStorageRoomGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new PressingStorageRoomGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (WealizhModBlocks.PRESSING_STORAGE_ROOM.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (WealizhModBlocks.PRESSING_MACHINE.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("PressingStorageRoomGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new PressingStorageRoomGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (WealizhModBlocks.COMPRESSOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (WealizhModBlocks.COMPRESSION_CHAMBER.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("CompressorGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new CompressorGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (WealizhModBlocks.COMPRESSION_CHAMBER.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (WealizhModBlocks.COMPRESSOR.get() == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y - 1, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("CompressorGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new CompressorGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (WealizhModBlocks.PLACER.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (entity.isShiftKeyDown()) {
				if (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
						&& !(Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem())) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack103 = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).copy();
						_setstack103.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount());
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack103);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
						_itemHandlerModifiable.setStackInSlot(0, ItemStack.EMPTY);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
				} else if (!(((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).getItem() == ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy()).getItem())
						&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() != (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount()) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					item_i = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).copy();
					item_num = itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount();
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy()).copy();
						_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount());
						_itemHandlerModifiable.setStackInSlot(0, _setstack);
					}
					if (!(entity instanceof Player _plr123 && _plr123.gameMode() == GameType.CREATIVE)) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack124 = item_i.copy();
							_setstack124.setCount((int) item_num);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack124);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
				}
			}
		}
		if ((Blocks.CHIPPED_ANVIL == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() || Blocks.DAMAGED_ANVIL == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock())
				&& WealizhModItems.IRON_BILLETSMATERIAL.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && entity.isShiftKeyDown()) {
			if (!(entity instanceof Player _plr134 && _plr134.gameMode() == GameType.CREATIVE)) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(WealizhModItems.IRON_BILLETSMATERIAL.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
			}
			if (Blocks.CHIPPED_ANVIL == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.ANVIL.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
					BlockEntity _be = world.getBlockEntity(_bp);
					CompoundTag _bnbt = null;
					if (_be != null) {
						_bnbt = _be.saveWithFullMetadata(world.registryAccess());
						_be.setRemoved();
					}
					world.setBlock(_bp, _bs, 3);
					if (_bnbt != null) {
						_be = world.getBlockEntity(_bp);
						if (_be != null) {
							try {
								_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
							} catch (Exception ignored) {
							}
						}
					}
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.CHIPPED_ANVIL.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
					BlockEntity _be = world.getBlockEntity(_bp);
					CompoundTag _bnbt = null;
					if (_be != null) {
						_bnbt = _be.saveWithFullMetadata(world.registryAccess());
						_be.setRemoved();
					}
					world.setBlock(_bp, _bs, 3);
					if (_bnbt != null) {
						_be = world.getBlockEntity(_bp);
						if (_be != null) {
							try {
								_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
							} catch (Exception ignored) {
							}
						}
					}
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.anvil.place")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.anvil.place")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0.01);
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		}
		if (WealizhModBlocks.EXPERIENCE_ABSORBER.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack150 = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).copy();
				_setstack150.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount());
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack150);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
				_itemHandlerModifiable.setStackInSlot(0, ItemStack.EMPTY);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
		}
		if (WealizhModBlocks.EXPERIENCE_RELEASE_DEVICE.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (entity.isShiftKeyDown()) {
				if (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
						&& !(Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem())) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack164 = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).copy();
						_setstack164.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount());
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack164);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
						_itemHandlerModifiable.setStackInSlot(0, ItemStack.EMPTY);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:empty_discharge")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
				} else if (!(((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).getItem() == ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy()).getItem())
						&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() != (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount()) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					item_i = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy()).copy();
					item_num = itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount();
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy()).copy();
						_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount());
						_itemHandlerModifiable.setStackInSlot(0, _setstack);
					}
					if (!(entity instanceof Player _plr184 && _plr184.gameMode() == GameType.CREATIVE)) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack185 = item_i.copy();
							_setstack185.setCount((int) item_num);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack185);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
				}
			}
		}
		if (getPropertyByName(blockstate, "qc_bool") instanceof BooleanProperty _getbp189 && blockstate.getValue(_getbp189)
				&& WealizhModItems.LAPIS_LAZULI_POWDER.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && !entity.isShiftKeyDown()) {
			if (!(entity instanceof Player _plr193 && _plr193.gameMode() == GameType.CREATIVE)) {
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
			}
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("qc_bool") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
			world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.LAPIS_BLOCK.defaultBlockState()));
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0);
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		}
		if (!(getPropertyByName(blockstate, "qc_bool") instanceof BooleanProperty _getbp201 && blockstate.getValue(_getbp201)) && Items.CLAY_BALL == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
				&& !entity.isShiftKeyDown()) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("qc_bool") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.LAPIS_BLOCK.defaultBlockState()));
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0);
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		}
		if (WealizhModBlocks.CENTRIFUGE.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
				&& (Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() || !entity.isShiftKeyDown())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (WealizhModBlocks.CENTRIFUGE_AROUND_PIPE.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("CentrifugeGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new CentrifugeGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u7ED3\u6784\u6B8B\u7F3A\uFF01" : "\u00A7cStructural Deficiency!")),
							true);
			}
		}
		if (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:logistics_pipe")))) {
			if (WealizhModItems.WRENCH.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("LogisticsPipeGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new LogisticsPipeGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
			} else if (WealizhModItems.WRENCH.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("LogisticsPipeGUI");
						}

						@Override
						public boolean shouldTriggerClientSideContainerClosingOnOpen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new LogisticsPipeGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.OFF_HAND, true);
			}
		}
		if (WealizhModItems.CREATIVE_PLANNING_PAPER_NBT.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("CreativePlanningPaperNBTGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new CreativePlanningPaperNBTGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:pickaxes"))) && WealizhModBlocks.CHARRED_BEDROCK.get() == blockstate.getBlock()) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (!(entity instanceof Player _plr238 && _plr238.gameMode() == GameType.CREATIVE)) {
				if (world instanceof ServerLevel _level) {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(Mth.nextInt(RandomSource.create(), 1, 24), _level, null, _stkprov -> {
					});
				}
			}
			if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(WealizhModBlocks.CHARRED_BEDROCK.get().defaultBlockState()));
				world.setBlock(BlockPos.containing(x, y, z), Blocks.BEDROCK.defaultBlockState(), 3);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
		}
		if (WealizhModItems.PLANNING_PAPER_ELECTRICITY_BAR.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("PlanningPaperSONGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new PlanningPaperSONGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if (WealizhModItems.PLANNING_PAPER_LIQUID_PIPE.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:pipe")))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("PlanningPaperSONGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new PlanningPaperSONGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if (WealizhModItems.PLANNING_PAPER_CONCENTRATED_ASSIGN_PRIORITIES.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
				&& (WealizhModBlocks.CONCENTRATED_WIRE.get() == blockstate.getBlock() || WealizhModBlocks.CONCENTRATED_PIPE.get() == blockstate.getBlock())) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("PlanningPaperConcentratedAssignPrioritiesGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new PlanningPaperConcentratedAssignPrioritiesGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if (WealizhModItems.PLANNING_PAPER_TRANSMISSION_VOLUME.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
				|| blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:pipe"))) || blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:logistics_pipe"))))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("PlanningPaperTrVoGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new PlanningPaperTrVoGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if (WealizhModItems.PLANNING_PAPER_REDSTONE_LOCK.get() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() && (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
				|| blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:pipe"))) || blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:logistics_pipe"))))) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("RS_Lock", false);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("RS_Lock", true);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]")
						? "\u5DF2\u5C06\"\u7EA2\u77F3\u9501\"\u8BA1\u5212\u4E3A\uFF1A"
						: "The \"Red Stone Lock\" project has been designated as:") + "" + (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock") ? "\u00A7aTrue" : "\u00A7cFalse"))), true);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), 8, 0.5, 0.5, 0.5, 0);
		}
		if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get() == blockstate.getBlock() && (getPropertyByName(blockstate, "cumt") instanceof IntegerProperty _getip295 ? blockstate.getValue(_getip295) : -1) < 15) {
			if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get().asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				{
					int _value = 1 + (getPropertyByName(blockstate, "cumt") instanceof IntegerProperty _getip300 ? blockstate.getValue(_getip300) : -1);
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("cumt") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (!(entity instanceof Player _plr302 && _plr302.gameMode() == GameType.CREATIVE)) {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.sand.place")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.sand.place")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			} else if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get().asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem()) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.OFF_HAND, true);
				{
					int _value = 1 + (getPropertyByName(blockstate, "cumt") instanceof IntegerProperty _getip310 ? blockstate.getValue(_getip310) : -1);
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("cumt") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (!(entity instanceof Player _plr312 && _plr312.gameMode() == GameType.CREATIVE)) {
					(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.sand.place")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.sand.place")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}