package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.WaterPushEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class NoPush extends Module {
	
	public NoPush() {
		super ("noPush", "u cant get pushed, and u cant push.", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@EventHandler
	private final Listener<WaterPushEvent> waterPushEventListener = new Listener<>(event -> {
			event.cancel();
	});
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
}

// Refrenced in MixinEntity