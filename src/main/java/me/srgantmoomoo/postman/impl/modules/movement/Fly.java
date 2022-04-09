package me.srgantmoomoo.postman.impl.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.util.world.EntityUtil;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;

/*
 * this is as basic as it gets, im taking this from an old jackiehack release lol.
 */

public class Fly extends Module {
	public NumberSetting speed = new NumberSetting("speed", this, 10, 1, 20, 1);
	public ModeSetting mode = new ModeSetting("mode", this, "vanilla", "vanilla", "packet");
	
	public Fly() {
		super("fly", "its a bird, its a plane!", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(speed, mode);
	}

    @Override
    public void onEnable() {
        if(mc.player == null || mc.world == null)
        	return;
        
        if(mode.is("vanilla")) {
        	mc.player.capabilities.isFlying = true;
            if (mc.player.capabilities.isCreativeMode) return;
            mc.player.capabilities.allowFlying = true;	
        }
    }
    
    @Override
    public void onDisable() {
    	if(mc.player == null || mc.world == null)
        	return;
    	
    	if(mode.is("vanilla")) {
    		mc.player.capabilities.isFlying = false;
            mc.player.capabilities.setFlySpeed(0.05f);
            if (mc.player.capabilities.isCreativeMode) return;
            mc.player.capabilities.allowFlying = false;	
    	}
    }


    @Override
    public void onUpdate() {
    	if(mode.is("vanilla")) {
    		mc.player.capabilities.setFlySpeed((float) (speed.getValue() / 100f));
            mc.player.capabilities.isFlying = true;
            if (mc.player.capabilities.isCreativeMode) return;
            mc.player.capabilities.allowFlying = true;
    	}
    	
    	if(mode.is("packet")) {
    		int angle;

            boolean forward = mc.gameSettings.keyBindForward.isKeyDown();
            boolean left = mc.gameSettings.keyBindLeft.isKeyDown();
            boolean right = mc.gameSettings.keyBindRight.isKeyDown();
            boolean back = mc.gameSettings.keyBindBack.isKeyDown();

            if (left && right) angle = forward ? 0 : back ? 180 : -1;
            else if (forward && back) angle = left ? -90 : (right ? 90 : -1);
            else {
                angle = left ? -90 : (right ? 90 : 0);
                if (forward) angle /= 2;
                else if (back) angle = 180-(angle/2);
            }

            if (angle != -1 && (forward || left || right || back)) {
                float yaw = mc.player.rotationYaw+angle;
                mc.player.motionX = EntityUtil.getRelativeX(yaw) * 0.2f;
                mc.player.motionZ = EntityUtil.getRelativeZ(yaw) * 0.2f;
            }

            mc.player.motionY = 0;
            mc.player.connection.sendPacket(new CPacketPlayer.PositionRotation(mc.player.posX + mc.player.motionX, mc.player.posY + (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0622 : 0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0622 : 0), mc.player.posZ + mc.player.motionZ, mc.player.rotationYaw, mc.player.rotationPitch, false));
            mc.player.connection.sendPacket(new CPacketPlayer.PositionRotation(mc.player.posX + mc.player.motionX, mc.player.posY - 42069, mc.player.posZ + mc.player.motionZ, mc.player.rotationYaw , mc.player.rotationPitch, true));
            	
        }
    }
    
    public double[] moveLooking() {
        return new double[] { mc.player.rotationYaw * 360.0F / 360.0F * 180.0F / 180.0F, 0.0D };
    }
}
