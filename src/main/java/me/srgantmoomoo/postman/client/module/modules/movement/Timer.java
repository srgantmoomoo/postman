package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PlayerMoveEvent;
import me.srgantmoomoo.postman.api.util.world.EntityUtil;
import me.srgantmoomoo.postman.api.util.world.JTimer;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.MobEffects;

/*
 * strafe is iffy rn, vanilla obvi doesn't work in most cases, strafe utils 
 */

public class Timer extends Module {
	public NumberSetting timerSpeed = new NumberSetting("timerSpeed", this, 1.15, 1, 1.5, 0.01);
	public Timer() {
		super ("Timer", "timer go zooooooooooom", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(timerSpeed);
	}
	private JTimer timer = new JTimer();
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
    mc.thePlayer.timerSpeed = timerSpeed
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		timer.reset();
	}
	
	public void onUpdate() {
		if(mc.player == null || mc.world == null) {
			disable();
			return;
		}	
