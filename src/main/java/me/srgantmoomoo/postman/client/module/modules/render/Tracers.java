package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.api.util.Wrapper;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.api.util.render.JTessellator;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

/*
 * written by @SrgantMooMoo on November 1st, 2020. hbd peep!
 */

public class Tracers extends Module {
	public BooleanSetting hostileMobs = new BooleanSetting("hostiles", this, false);
	public BooleanSetting passiveMobs = new BooleanSetting("passives", this, false);
	public BooleanSetting players = new BooleanSetting("players", this, true);
	public NumberSetting pRed = new NumberSetting("pRed", this, 0.0, 0.0, 1.0, 0.1);
	public NumberSetting pGreen = new NumberSetting("pGreen", this, 0.6, 0.0, 1.0, 0.1);
	public NumberSetting pBlue = new NumberSetting("pBlue", this, 1.0, 0.0, 1.0, 0.1);
	
	public Tracers() {
		super ("tracers", "draws line to entitys and/or sotrage", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(players, hostileMobs, passiveMobs, pRed, pGreen, pBlue);
	}
	private static final Minecraft mc = Wrapper.getMinecraft();

	public void onWorldRender(RenderEvent event){
		
		if(!this.toggled)
			return;
		
		for(Object theObject : mc.world.loadedEntityList) {
			if(!(theObject instanceof EntityLivingBase))
				continue;
		
			EntityLivingBase entity = (EntityLivingBase) theObject;
			
			if(entity instanceof EntityPlayer) {
				if(entity != mc.player)
					player(entity);
					continue;
			}
			if(entity instanceof EntityAnimal) {
				passive(entity);
				continue;
			}
			hostile(entity);
		}
		
			super.onWorldRender(event);
			
	}
		
		/*mc.world.loadedEntityList.stream()
				.filter(e->e instanceof EntityPlayer)
				.filter(e->e != mc.player)
				.forEach(e->{
					GlStateManager.pushMatrix();
					GlStateManager.popMatrix();
				});*/
	
	public void player(EntityLivingBase entity) {
		if(players.isEnabled()) {
		float red = (float) pRed.getValue();
		float green = (float) pGreen.getValue();
		float blue = (float) pBlue.getValue();
		
		double xPos = (entity.posX);
		double yPos = (entity.posY);
		double zPos = (entity.posZ);
		
		render(red, green, blue, xPos, yPos, zPos);
		
		}
	}
	
	public void passive(EntityLivingBase entity) {
		if(passiveMobs.isEnabled()) {
		float red = 0F;
		float green = 1F;
		float blue = 0.0F;
		
		double xPos = (entity.posX);
		double yPos = (entity.posY);
		double zPos = (entity.posZ);
		
		render(red, green, blue, xPos, yPos, zPos);
		}
	}

	public void hostile(EntityLivingBase entity) {
		if(hostileMobs.isEnabled()) {
		float red = 1F;
		float green = 0F;
		float blue = 0F;
		
		double xPos = (entity.posX);
		double yPos = (entity.posY);
		double zPos = (entity.posZ);
		
		render(red, green, blue, xPos, yPos, zPos);
		}
	}

	
	public void render(float red, float green, float blue, double posx, double posy, double posz) {
		Vec3d eyes=ActiveRenderInfo.getCameraPosition().add(mc.getRenderManager().viewerPosX,mc.getRenderManager().viewerPosY,mc.getRenderManager().viewerPosZ);
		JTessellator.drawLine(eyes.x, eyes.y, eyes.z, posx, posy + 1.2, posz, red, green, blue, 2f);
	}
}