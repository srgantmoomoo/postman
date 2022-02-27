package me.srgantmoomoo.postman.client.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.util.damagetilt.MessageUpdateAttackYaw;
import me.srgantmoomoo.postman.backend.util.damagetilt.PacketHandler;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageTiltCorrection extends Module {
	
	public DamageTiltCorrection() {
		super ("damageTilt", "fixes minecraft's age old damage tilt bug.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@SubscribeEvent
	public void onKnockback(LivingKnockBackEvent event) {
	    if (event.getEntityLiving() instanceof EntityPlayer) {
	    	EntityPlayer player = (EntityPlayer)event.getEntityLiving();
	    	if (player.world.isRemote)
	    		return; 
	    	PacketHandler.instance.sendTo(new MessageUpdateAttackYaw((EntityLivingBase)player), (EntityPlayerMP)player);
	    }
	}
	
	@Override
	public void onEnable() {
		PacketHandler.init();
	}
	
}
