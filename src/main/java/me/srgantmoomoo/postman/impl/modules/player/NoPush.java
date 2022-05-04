package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.postman.backend.event.Event;
import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.WaterPushEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class NoPush extends Module {
	
	public NoPush() {
		super ("noPush", "u cant get pushed, and u cant push.", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@EventHandler
	private final Listener<WaterPushEvent> waterPushEventListener = new Listener<>(Event::cancel);
}

// Refrenced in MixinEntity