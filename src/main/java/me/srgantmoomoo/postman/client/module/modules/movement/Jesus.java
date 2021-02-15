package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.Event.Era;
import me.srgantmoomoo.postman.api.event.events.LiquidCollisionBBEvent;
import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.api.event.events.PlayerUpdateMoveStateEvent;
import me.srgantmoomoo.postman.api.util.world.EntityUtil;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Jesus extends Module {
	
	public Jesus() {
		super ("jesus", "lets u walk on water.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	public float offset = 0.5f;
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisbale() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
    private final Listener<LiquidCollisionBBEvent> getLiquidCollisionBB = new Listener<>(event -> {
    	if(toggled) {
	    	if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
	            if (this.checkCollide() && !(Minecraft.getMinecraft().player.motionY >= 0.1f) && event.getBlockPos().getY() < Minecraft.getMinecraft().player.posY - this.offset) {
	                if (Minecraft.getMinecraft().player.getRidingEntity() != null) {
	                    event.setBoundingBox(new AxisAlignedBB(0, 0, 0, 1, 1 - this.offset, 1));
	                } else {
	                        event.setBoundingBox(Block.FULL_BLOCK_AABB);
	                    }
	                }
	                event.cancel();
	            }
    	}
    });
	
	@EventHandler
    private final Listener<PlayerUpdateMoveStateEvent> updateWalkingPlayer = new Listener<>(event -> {
    	if(toggled) {
	    	if (event.getEra() == Era.PRE) {
	            if (!Minecraft.getMinecraft().player.isSneaking() && !Minecraft.getMinecraft().player.noClip && !Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && EntityUtil.isInLiquid()) {
	                Minecraft.getMinecraft().player.motionY = 0.1f;
	            }
	        }
    	}
    });

    private boolean checkCollide() {
        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.player.isSneaking()) {
            return false;
        }

        if (mc.player.getRidingEntity() != null) {
            if (mc.player.getRidingEntity().fallDistance >= 3.0f) {
                return false;
            }
        }

        if (mc.player.fallDistance >= 3.0f) {
            return false;
        }

        return true;
    }

}
