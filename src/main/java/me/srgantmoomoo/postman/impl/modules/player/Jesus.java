package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.Event.Era;
import me.srgantmoomoo.postman.backend.event.events.LiquidCollisionBBEvent;
import me.srgantmoomoo.postman.backend.event.events.PlayerUpdateMoveStateEvent;
import me.srgantmoomoo.postman.backend.util.world.EntityUtil;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;

public class Jesus extends Module {
	
	public Jesus() {
		super ("jesus", "lets u walk on water.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	public float offset = 0.5f;
	
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
