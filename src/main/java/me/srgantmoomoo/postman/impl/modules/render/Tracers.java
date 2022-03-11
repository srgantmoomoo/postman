package me.srgantmoomoo.postman.impl.modules.render;

import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
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

import java.util.List;
import java.util.stream.Collectors;

/*
 * written by @SrgantMooMoo on November 1st, 2020. hbd peep!
 */

/**
 * rewrite interpolate taken from gs.
 * @author SrgantMooMoo
 * @since 3/10/2022
 */

public class Tracers extends Module {
	public BooleanSetting players = new BooleanSetting("players", this, true);
	public BooleanSetting hostileMobs = new BooleanSetting("hostiles", this, false);
	public BooleanSetting passiveMobs = new BooleanSetting("passives", this, false);
	public ColorSetting playerColor = new ColorSetting("playerColor", this, new JColor(255, 255, 255, 255));
	public ColorSetting hostileMobColor = new ColorSetting("hostileMobColor", this, new JColor(255, 000, 000, 255));
	public ColorSetting passiveMobColor = new ColorSetting("passiveMobColor", this, new JColor(000, 255, 000, 255));

	public Tracers() {
		super("tracers", "draws line to entitys.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(players, hostileMobs, passiveMobs, playerColor, hostileMobColor, passiveMobColor);
	}
	List<Entity> entities;

	@Override
	public void onWorldRender(RenderEvent event) {
		entities = mc.world.loadedEntityList.stream().filter(entity -> entity != mc.player).collect(Collectors.toList());

		entities.forEach(entity -> {
			Vec3d eyes = ActiveRenderInfo.getCameraPosition().add(mc.getRenderManager().viewerPosX, mc.getRenderManager().viewerPosY, mc.getRenderManager().viewerPosZ);

			if(entity instanceof EntityPlayer && players.isEnabled()) {
				JTessellator.drawLine(eyes.x, eyes.y, eyes.z, interpolate(entity.posX, entity.lastTickPosX), interpolate(entity.posY, entity.lastTickPosY), interpolate(entity.posZ, entity.lastTickPosZ), playerColor.getValue());
			}else if(entity instanceof EntityAnimal && passiveMobs.isEnabled()) {
				JTessellator.drawLine(eyes.x, eyes.y, eyes.z, interpolate(entity.posX, entity.lastTickPosX), interpolate(entity.posY, entity.lastTickPosY), interpolate(entity.posZ, entity.lastTickPosZ), passiveMobColor.getValue());
			}else if((entity instanceof EntityCreature || entity instanceof EntitySlime) && hostileMobs.isEnabled()) {
				JTessellator.drawLine(eyes.x, eyes.y, eyes.z, interpolate(entity.posX, entity.lastTickPosX), interpolate(entity.posY, entity.lastTickPosY), interpolate(entity.posZ, entity.lastTickPosZ), hostileMobColor.getValue());
			}
		});
	}

	// this was taken from gamesnse.
	private double interpolate(double now, double then) {
		return then + (now - then) * mc.getRenderPartialTicks();
	}
}
