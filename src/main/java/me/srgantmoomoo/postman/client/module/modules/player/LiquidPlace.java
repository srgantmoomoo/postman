package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.CanCollideCheckEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class LiquidPlace extends Module {
	
	public LiquidPlace() {
		super ("liquidPlace", "lets u place blocks on liquid.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@EventHandler
	private Listener<CanCollideCheckEvent> CanCollid = new Listener<>(event -> {
		event.cancel();
	});
}
