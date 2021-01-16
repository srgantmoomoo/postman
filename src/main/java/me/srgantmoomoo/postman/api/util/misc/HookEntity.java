package me.srgantmoomoo.postman.api.util.misc;

import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class HookEntity {
	public static void setVelocity(Entity entity, double x, double y, double z) {
		if(!ModuleManager.getModuleByName("DamageTiltCorrection").isToggled())
			return;
		
		//This is run before motion is locally set
		
		if(entity != null) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			if(player != null && entity.equals(player)) {
				//Set the value
				float result = (float)(MathHelper.atan2(player.motionZ - z, player.motionX - x) * (180D / Math.PI) - (double)player.rotationYaw);
				
				if(Float.isFinite(result))
					player.attackedAtYaw = result;
			}
		}
	}
}