package me.srgantmoomoo.postman.impl.modules.pvp;

import static me.srgantmoomoo.postman.backend.util.world.BlockUtils.faceVectorPacketInstant;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.util.world.BlockUtils;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/*
 * Almost completely stolen from gamesense. @Srgantmoomoo November 6th, 2020
 */
public class Surround extends Module {
	private final Minecraft mc = Minecraft.getMinecraft();

	public BooleanSetting triggerSurround = new BooleanSetting("trigger", this, false);
	public BooleanSetting shiftOnly = new BooleanSetting("onShift", this, false);
	public BooleanSetting rotate = new BooleanSetting("rotate", this, true);
	public BooleanSetting disableOnJump = new BooleanSetting("offJump", this, false);
	public BooleanSetting centerPlayer = new BooleanSetting("autoCenter", this, true);
	public NumberSetting tickDelay = new NumberSetting("tickDelay", this, 5, 0, 10, 1);
	public NumberSetting timeOutTicks = new NumberSetting("timeOutTicks", this, 40, 1, 100, 10);
	public NumberSetting blocksPerTick = new NumberSetting("blocksPerTick", this, 4, 0, 8, 1);
	
	public Surround() {
		super ("surround", "automatically surrounds u in obby.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(triggerSurround, shiftOnly, rotate, disableOnJump, centerPlayer, tickDelay, timeOutTicks, blocksPerTick);
	}

	@SuppressWarnings("unused")
	private boolean noObby = false;
	private boolean isSneaking = false;
	private boolean firstRun = false;

	private int oldSlot = -1;

	private int runTimeTicks = 0;
	private int delayTimeTicks = 0;
	private int offsetSteps = 0;

	private Vec3d centeredBlock = Vec3d.ZERO;

	public static Vec3d getInterpolatedPos(Entity entity, float ticks) {
		return (new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ)).add(getInterpolatedAmount(entity, ticks));
	}

	public static Vec3d getInterpolatedAmount(Entity entity, double ticks) {
		return getInterpolatedAmount(entity, ticks, ticks, ticks);
	}

	public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
		return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
	}

	@Override
	public void onEnable() {
		if (mc.player == null) {
			disable();
			return;
		}

		if (centerPlayer.isEnabled() && mc.player.onGround) {
			mc.player.motionX = 0;
			mc.player.motionZ = 0;
		}

		centeredBlock = getCenterOfBlock(mc.player.posX, mc.player.posY, mc.player.posY);

		oldSlot = mc.player.inventory.currentItem;

		if (findObsidianSlot() != -1) {
			mc.player.inventory.currentItem = findObsidianSlot();
		}
	}

	@Override
	public void onDisable() {
		if (mc.player == null) {
			return;
		}

		if (isSneaking){
			mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
			isSneaking = false;
		}

		if (oldSlot != mc.player.inventory.currentItem && oldSlot != -1) {
			mc.player.inventory.currentItem = oldSlot;
			oldSlot = -1;
		}

		centeredBlock = Vec3d.ZERO;

		noObby = false;
		firstRun = true;
	}

	@Override
	public void onUpdate() {
		if (mc.player == null) {
			disable();
			return;
		}

		if (mc.player.posY <= 0) {
			return;
		}

		if (firstRun){
			firstRun = false;
			if (findObsidianSlot() == -1 ) {
				noObby = true;
				disable();
			}
		}
		else {
			if (delayTimeTicks < tickDelay.getValue()) {
				delayTimeTicks++;
				return;
			}
			else {
				delayTimeTicks = 0;
			}
		}

		if (shiftOnly.isEnabled() && !mc.player.isSneaking()) {
			return;
		}

		if (disableOnJump.isEnabled() && !(mc.player.onGround) && !(mc.player.isInWeb)) {
			return;
		}

		if (centerPlayer.isEnabled() && centeredBlock != Vec3d.ZERO && mc.player.onGround) {

			double xDeviation = Math.abs(centeredBlock.x - mc.player.posX);
			double zDeviation = Math.abs(centeredBlock.z - mc.player.posZ);

			if (xDeviation <= 0.1 && zDeviation <= 0.1){
				centeredBlock = Vec3d.ZERO;
			}
			else {
				double newX;
				double newZ;
				if (mc.player.posX > Math.round(mc.player.posX)) {
				   newX = Math.round(mc.player.posX) + 0.5;
				}
				else if (mc.player.posX < Math.round(mc.player.posX)) {
					newX = Math.round(mc.player.posX) - 0.5;
				}
				else {
					newX = mc.player.posX;
				}

				if (mc.player.posZ > Math.round(mc.player.posZ)) {
					newZ = Math.round(mc.player.posZ) + 0.5;
				}
				else if (mc.player.posZ < Math.round(mc.player.posZ)) {
					newZ = Math.round(mc.player.posZ) - 0.5;
				}
				else {
					newZ = mc.player.posZ;
				}

				mc.player.connection.sendPacket(new CPacketPlayer.Position(newX, mc.player.posY, newZ, true));
				mc.player.setPosition(newX, mc.player.posY, newZ);
			}
		}

		if (triggerSurround.isEnabled() && runTimeTicks >= timeOutTicks.getValue()) {
			runTimeTicks = 0;
			disable();
			return;
		}

		int blocksPlaced = 0;

		while (blocksPlaced <= blocksPerTick.getValue()) {
			Vec3d[] offsetPattern;
			offsetPattern = Surround.Offsets.SURROUND;
			int maxSteps = Surround.Offsets.SURROUND.length;

			if (offsetSteps >= maxSteps){
				offsetSteps = 0;
				break;
			}

			BlockPos offsetPos = new BlockPos(offsetPattern[offsetSteps]);
			BlockPos targetPos = new BlockPos(mc.player.getPositionVector()).add(offsetPos.getX(), offsetPos.getY(), offsetPos.getZ());

			boolean tryPlacing = mc.world.getBlockState(targetPos).getMaterial().isReplaceable();

			for (Entity entity : mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(targetPos))) {
				if (entity instanceof EntityPlayer) {
					tryPlacing = false;
					break;
				}
			}

			if (tryPlacing && placeBlock(targetPos)) {
				blocksPlaced++;
			}

			offsetSteps++;

			if (isSneaking) {
				mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
				isSneaking = false;
			}
		}
		runTimeTicks++;
	}

	private int findObsidianSlot() {
		int slot = -1;

		for (int i = 0; i < 9; i++) {
			ItemStack stack = mc.player.inventory.getStackInSlot(i);

			if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock)) {
				continue;
			}

			Block block = ((ItemBlock) stack.getItem()).getBlock();
			if (block instanceof BlockObsidian){
				slot = i;
				break;
			}
		}
		return slot;
	}

	private boolean placeBlock(BlockPos pos) {
		Block block = mc.world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
			return false;
		}

		EnumFacing side = BlockUtils.getPlaceableSide(pos);

		if (side == null){
			return false;
		}

		BlockPos neighbour = pos.offset(side);
		EnumFacing opposite = side.getOpposite();

		if (!BlockUtils.canBeClicked(neighbour)) {
			return false;
		}

		Vec3d hitVec = new Vec3d(neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
		Block neighbourBlock = mc.world.getBlockState(neighbour).getBlock();

		int obsidianSlot = findObsidianSlot();

		if (mc.player.inventory.currentItem != obsidianSlot && obsidianSlot != -1) {

			mc.player.inventory.currentItem = obsidianSlot;
		}

		if (!isSneaking && BlockUtils.blackList.contains(neighbourBlock) || BlockUtils.shulkerList.contains(neighbourBlock)) {
			mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
			isSneaking = true;
		}

		if (obsidianSlot == -1) {
			noObby = true;
			return false;
		}

		if (rotate.isEnabled()) {
			faceVectorPacketInstant(hitVec);
		}

		mc.playerController.processRightClickBlock(mc.player, mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
		mc.player.swingArm(EnumHand.MAIN_HAND);
		mc.rightClickDelayTimer = 4;

		return true;
	}

	private Vec3d getCenterOfBlock(double playerX, double playerY, double playerZ) {
		double newX = Math.floor(playerX) + 0.5;
		double newY = Math.floor(playerY);
		double newZ = Math.floor(playerZ) + 0.5;

		return new Vec3d(newX, newY, newZ);
	}

	private static class Offsets {
		private static final Vec3d[] SURROUND = {
			new Vec3d(1, 0, 0),
			new Vec3d(0, 0, 1),
			new Vec3d(-1, 0, 0),
			new Vec3d(0, 0, -1),
			new Vec3d(1, -1, 0),
			new Vec3d(0, -1, 1),
			new Vec3d(-1, -1, 0),
			new Vec3d(0, -1, -1)
		};
	}
}
