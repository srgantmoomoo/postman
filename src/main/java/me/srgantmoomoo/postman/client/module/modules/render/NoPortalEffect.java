package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.GuiIngameForge;

/*
 * lost a fight cause of fucking portal effect and i couldn't see shit, so this is my solution :)
 */

public class NoPortalEffect extends Module {
	public BooleanSetting noNausea = new BooleanSetting("noNausea", this, true);
	public BooleanSetting noOverlay = new BooleanSetting("noOverlay", this, true);
	
	public NoPortalEffect() {
		super("noPortalEffect", "stops the portal effect from rendering.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
		if(noOverlay.isEnabled()) GuiIngameForge.renderPortal = false;
	}
	
	public void onUpdate() {
		if(noNausea.isEnabled()) mc.player.removeActivePotionEffect(MobEffects.NAUSEA);
	}
	
	public void onDisable() {
		GuiIngameForge.renderPortal = true;
	}
}
