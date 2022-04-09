 package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 * Rewritten by @SrgantMooMoo on 1/4/21.
 */

public class FullBright extends Module {
	
	public FullBright() {
		super ("fullBright", "makes everything fully bright.", Keyboard.KEY_NONE, Category.RENDER);
	}
	 private float lastGamma;

	 @Override
	 public void onEnable() {
	     lastGamma = mc.gameSettings.gammaSetting;
	 }

	 @Override
	 public void onDisable() {
	     mc.gameSettings.gammaSetting = this.lastGamma;
	 }

	 @EventHandler
	 private final Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(p_Event -> {
	     mc.gameSettings.gammaSetting = 1000;
         mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
	 });
}