package me.srgantmoomoo.postman.impl.modules.player;

import me.zero.alpine.type.Cancellable;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.CanCollideCheckEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class LiquidPlace extends Module {
	public LiquidPlace() {
		super ("liquidPlace", "lets u place blocks on liquid.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@EventHandler
	private final Listener<CanCollideCheckEvent> canCollide = new Listener<>(Cancellable::cancel);
}
