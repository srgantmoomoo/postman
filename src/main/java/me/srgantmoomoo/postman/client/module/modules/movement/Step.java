package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Step extends Module {
	
	public NumberSetting height = new NumberSetting("height", this, 1, 0.5, 10, 0.5);

	
	public Step() {
		super ("step", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(height);
	}
	    private final double[] oneblockPositions = {0.42D, 0.75D};

	    private final double[] twoblockPositions = {0.4D, 0.75D, 0.5D, 0.41D, 0.83D, 1.16D, 1.41D, 1.57D, 1.58D, 1.42D};

	    private double[] selectedPositions = new double[0];

	    private int packets;
	    
	    public void onUpdate() {
	    	
	    	if(height.getValue() <= 1) this.selectedPositions = this.oneblockPositions;
	    	else if(height.getValue() >= 1.5) this.selectedPositions = this.oneblockPositions;
	    	
            if (mc.player.collidedHorizontally && mc.player.onGround) {
                this.packets++;
            }

            //check if there is a block above our head
            final AxisAlignedBB bb = mc.player.getEntityBoundingBox();

            for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX + 1.0D); x++) {
                for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ + 1.0D); z++) {
                    final Block block = mc.world.getBlockState(new BlockPos(x, bb.maxY + 1, z)).getBlock();
                    if (!(block instanceof BlockAir)) {
                        return;
                    }
                }
            }

            if (mc.player.onGround && !mc.player.isInsideOfMaterial(Material.WATER) && !mc.player.isInsideOfMaterial(Material.LAVA) && !mc.player.isInWeb && mc.player.collidedVertically && mc.player.fallDistance == 0 && !mc.gameSettings.keyBindJump.isPressed() && mc.player.collidedHorizontally && !mc.player.isOnLadder() && this.packets > this.selectedPositions.length - 2) {
                for (double position : this.selectedPositions) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + position, mc.player.posZ, true));
                }
                mc.player.setPosition(mc.player.posX, mc.player.posY + this.selectedPositions[this.selectedPositions.length - 1], mc.player.posZ);
                this.packets = 0;
            }
	    	
	    
	}
}
