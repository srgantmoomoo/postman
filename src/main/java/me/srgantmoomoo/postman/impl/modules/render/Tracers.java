package me.srgantmoomoo.postman.impl.modules.render;

import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.backend.util.Wrapper;
import me.srgantmoomoo.postman.backend.util.render.JTessellator;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
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
	public ColorSetting playerColor = new ColorSetting("playerColor", this, new JColor(255, 255, 255, 255));
	
	public Tracers() {
		super ("tracers", "draws line to entitys.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(players, hostileMobs, passiveMobs, playerColor);
	}
	private static final Minecraft mc = Wrapper.getMinecraft();

	@Override
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
	
	public void player(EntityLivingBase entity) {
		if(players.isEnabled()) {
			double xPos = (entity.posX);
			double yPos = (entity.posY);
			double zPos = (entity.posZ);

			render(xPos, yPos, zPos);
		}
	}
	
	public void passive(EntityLivingBase entity) {
		if(passiveMobs.isEnabled()) {
			double xPos = (entity.posX);
			double yPos = (entity.posY);
			double zPos = (entity.posZ);

			render(xPos, yPos, zPos);
		}
	}

	public void hostile(EntityLivingBase entity) {
		if(hostileMobs.isEnabled()) {
			double xPos = (entity.posX);
			double yPos = (entity.posY);
			double zPos = (entity.posZ);

			render(xPos, yPos, zPos);
		}
	}

	
	public void render(double posx, double posy, double posz) {
		Vec3d eyes=ActiveRenderInfo.getCameraPosition().add(mc.getRenderManager().viewerPosX,mc.getRenderManager().viewerPosY,mc.getRenderManager().viewerPosZ);
		JTessellator.drawLine(eyes.x, eyes.y, eyes.z, posx, posy + 1.2, posz, playerColor.getValue());
	}
}
