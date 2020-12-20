package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class HoleTp extends Module {
	
	public HoleTp() {
		super ("holeTp", "automatically hits anything near u", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings();
	}

	public static Minecraft mc = Minecraft.getMinecraft();
	
	private int packets;
	private boolean jumped;
	private final double[] oneblockPositions = new double[]{ 0.42, 0.75};

	public void onUpdate(){
		if (HoleTp.mc.world == null || HoleTp.mc.player == null){
			return;
		}
			if (!HoleTp.mc.player.onGround){
				if (HoleTp.mc.gameSettings.keyBindJump.isKeyDown()){
					this.jumped = true;
				}
			}
			else{
				this.jumped = false;
			}
			if (!this.jumped && HoleTp.mc.player.fallDistance < 0.5 && this.isInHole() && HoleTp.mc.player.posY - this.getNearestBlockBelow() <= 1.125 && HoleTp.mc.player.posY - this.getNearestBlockBelow() <= 0.95 && !this.isOnLiquid() && !this.isInLiquid()){
				if (!HoleTp.mc.player.onGround){
					this.packets++;
				}
				if (!HoleTp.mc.player.onGround && !HoleTp.mc.player.isInsideOfMaterial(Material.WATER) && !HoleTp.mc.player.isInsideOfMaterial(Material.LAVA) && !HoleTp.mc.gameSettings.keyBindJump.isKeyDown() && !HoleTp.mc.player.isOnLadder() && this.packets > 0){
					final BlockPos blockPos = new BlockPos(HoleTp.mc.player.posX, HoleTp.mc.player.posY, HoleTp.mc.player.posZ);
					for (final double position : this.oneblockPositions){
						HoleTp.mc.player.connection.sendPacket(new CPacketPlayer.Position(blockPos.getX() + 0.5f, HoleTp.mc.player.posY - position, blockPos.getZ() + 0.5f, true));
					}
					HoleTp.mc.player.setPosition(blockPos.getX() + 0.5f, this.getNearestBlockBelow() + 0.1, blockPos.getZ() + 0.5f);
					this.packets = 0;
				}
			}
		}

	private boolean isInHole(){
		final BlockPos blockPos = new BlockPos(HoleTp.mc.player.posX, HoleTp.mc.player.posY, HoleTp.mc.player.posZ);
		final IBlockState blockState = HoleTp.mc.world.getBlockState(blockPos);
		return this.isBlockValid(blockState, blockPos);
	}

	private double getNearestBlockBelow(){
		for (double y = HoleTp.mc.player.posY; y > 0.0; y -= 0.001){
			if (!(HoleTp.mc.world.getBlockState(new BlockPos(HoleTp.mc.player.posX, y, HoleTp.mc.player.posZ)).getBlock() instanceof BlockSlab) && HoleTp.mc.world.getBlockState(new BlockPos(HoleTp.mc.player.posX, y, HoleTp.mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox(HoleTp.mc.world, new BlockPos(0, 0, 0)) != null){
				return y;
			}
		}
		return -1.0;
	}

	private boolean isBlockValid(final IBlockState blockState, final BlockPos blockPos){
		return blockState.getBlock() == Blocks.AIR && HoleTp.mc.player.getDistanceSq(blockPos) >= 1.0 && HoleTp.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR && HoleTp.mc.world.getBlockState(blockPos.up(2)).getBlock() == Blocks.AIR && (this.isBedrockHole(blockPos) || this.isObbyHole(blockPos) || this.isBothHole(blockPos) || this.isElseHole(blockPos));
	}

	private boolean isObbyHole(final BlockPos blockPos){
		final BlockPos[] array;
		final BlockPos[] touchingBlocks = array = new BlockPos[]{ blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
		for (final BlockPos touching : array){
			final IBlockState touchingState = HoleTp.mc.world.getBlockState(touching);
			if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.OBSIDIAN){
				return false;
			}
		}
		return true;
	}

	private boolean isBedrockHole(final BlockPos blockPos){
		final BlockPos[] array;
		final BlockPos[] touchingBlocks = array = new BlockPos[]{ blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
		for (final BlockPos touching : array){
			final IBlockState touchingState = HoleTp.mc.world.getBlockState(touching);
			if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.BEDROCK){
				return false;
			}
		}
		return true;
	}

	private boolean isBothHole(final BlockPos blockPos){
		final BlockPos[] array;
		final BlockPos[] touchingBlocks = array = new BlockPos[]{ blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
		for (final BlockPos touching : array){
			final IBlockState touchingState = HoleTp.mc.world.getBlockState(touching);
			if (touchingState.getBlock() == Blocks.AIR || (touchingState.getBlock() != Blocks.BEDROCK && touchingState.getBlock() != Blocks.OBSIDIAN)){
				return false;
			}
		}
		return true;
	}

	private boolean isElseHole(final BlockPos blockPos){
		final BlockPos[] array;
		final BlockPos[] touchingBlocks = array = new BlockPos[]{ blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
		for (final BlockPos touching : array){
			final IBlockState touchingState = HoleTp.mc.world.getBlockState(touching);
			if (touchingState.getBlock() == Blocks.AIR || !touchingState.isFullBlock()){
				return false;
			}
		}
		return true;
	}

	private boolean isOnLiquid(){
		final double y = HoleTp.mc.player.posY - 0.03;
		for (int x = MathHelper.floor(HoleTp.mc.player.posX); x < MathHelper.ceil(HoleTp.mc.player.posX); x++){
			for (int z = MathHelper.floor(HoleTp.mc.player.posZ); z < MathHelper.ceil(HoleTp.mc.player.posZ); z++){
				final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
				if (HoleTp.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid){
					return true;
				}
			}
		}
		return false;
	}

	private boolean isInLiquid(){
		final double y = HoleTp.mc.player.posY + 0.01;
		for (int x = MathHelper.floor(HoleTp.mc.player.posX); x < MathHelper.ceil(HoleTp.mc.player.posX); x++){
			for (int z = MathHelper.floor(HoleTp.mc.player.posZ); z < MathHelper.ceil(HoleTp.mc.player.posZ); z++){
				final BlockPos pos = new BlockPos(x, (int)y, z);
				if (HoleTp.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}