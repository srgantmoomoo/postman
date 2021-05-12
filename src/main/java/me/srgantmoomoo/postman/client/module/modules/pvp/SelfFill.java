package me.srgantmoomoo.postman.client.module.modules.pvp;

import static me.srgantmoomoo.postman.api.util.world.BlockUtils.faceVectorPacketInstant;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.util.world.BlockUtils;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SelfFill extends Module {

	public ModeSetting mode = new ModeSetting("mode", this, "instant", "instant", "jump", "tp");
	public BooleanSetting autoSwitch = new BooleanSetting("autoSwitch", this, true);
	public BooleanSetting rotations = new BooleanSetting("rotate", this, false);
	public NumberSetting offset = new NumberSetting("offset", this, 4, 0, 12, 0.1);
	public NumberSetting rubberbandDelay = new NumberSetting("delay", this, 13, 1, 30, 1);
	public BooleanSetting autoDisable = new BooleanSetting("autoDisable", this, true);
	
	private boolean placed;
	private boolean jumped;
	private BlockPos startPos;
	private int ticks;
	private int startSlot;
	
	public SelfFill() {
		super("selfFill", "kek", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode, autoSwitch, rotations, offset, autoDisable, rubberbandDelay);
	}
	
	@Override
	public void onEnable() {
		startPos = new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);
		startSlot = mc.player.inventory.currentItem;
		
		if (intersectsWithEntity(startPos) || findBlockSlot() == -1) {
            toggle();
            return;
        }
		
		if (autoSwitch.isEnabled()) {
			mc.player.connection.sendPacket(new CPacketHeldItemChange(findBlockSlot()));
			mc.playerController.updateController();
		}
		
		if (mode.is("instant")) {
			
	        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698D, mc.player.posZ, true));
	        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805211997D, mc.player.posZ, true));
	        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.00133597911214D, mc.player.posZ, true));
	        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.16610926093821D, mc.player.posZ, true));
			
			placeBlock(startPos, rotations.isEnabled(), true);
			mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + offset.getValue(), mc.player.posZ, true));
			
			if (autoSwitch.isEnabled()) {
				mc.player.connection.sendPacket(new CPacketHeldItemChange(startSlot));
				mc.playerController.updateController();
			}
			mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
			if(autoDisable.isEnabled()) toggle();
		}
		
	}
	
	@Override 
	public void onDisable() {
		placed = false;
		jumped = false;
	}
	
	
	@Override
	public void onUpdate() {
		if (mc.player == null || mc.world == null) return;
		if (!mode.is("instant")) {
			ticks++;
			if (!jumped) {
				mc.player.jump();
				jumped = true;
			}
			if (ticks % rubberbandDelay.getValue() == 0 && !placed) {
				placeBlock(startPos, rotations.isEnabled(), true);
				placed = true;
				mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + offset.getValue(), mc.player.posZ, true));
				if(mode.is("jump")) mc.player.jump();
				else mc.player.motionY = offset.getValue();
				if (autoSwitch.isEnabled()) {
					mc.player.connection.sendPacket(new CPacketHeldItemChange(startSlot));
					mc.playerController.updateController();
				}
				mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
				if(autoDisable.isEnabled()) toggle();
			}
		}
	}
	
	private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : mc.world.loadedEntityList) {
            if (entity.equals(mc.player)) continue;
            if (entity instanceof EntityItem) continue;
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) return true;
        }
        return false;
    }
	
    private boolean placeBlock(BlockPos pos, boolean rotate, boolean isSneaking) {
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

        int obsidianSlot = findBlockSlot();

        if (mc.player.inventory.currentItem != obsidianSlot && obsidianSlot != -1) {

            mc.player.inventory.currentItem = obsidianSlot;
        }

        if (!isSneaking && BlockUtils.blackList.contains(neighbourBlock) || BlockUtils.shulkerList.contains(neighbourBlock)) {
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
            isSneaking = true;
        }

        if (rotate) {
            faceVectorPacketInstant(hitVec);
        }

        mc.playerController.processRightClickBlock(mc.player, mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
        mc.player.swingArm(EnumHand.MAIN_HAND);
        mc.rightClickDelayTimer = 4;

        return true;
    }

    private int findBlockSlot() {
        int slot = -1;

        for (int i = 0; i < 9; i++){
            ItemStack stack = mc.player.inventory.getStackInSlot(i);

            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock)) {
                continue;
            }

            Block block = ((ItemBlock) stack.getItem()).getBlock();
            if (block instanceof BlockObsidian || block instanceof BlockEnderChest){
                slot = i;
                break;
            }
        }
        return slot;
    }
    
}
