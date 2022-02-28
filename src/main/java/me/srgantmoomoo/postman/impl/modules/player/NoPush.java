package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.WaterPushEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
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
}

// Refrenced in MixinEntity