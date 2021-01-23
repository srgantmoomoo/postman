package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.CanCollideCheckEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class LiquidInteract extends Module {
	
	public LiquidInteract() {
		super ("liquidInteract", "fly ez lololol", Keyboard.KEY_NONE, Category.EXPLOITS);
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);

	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
	private Listener<CanCollideCheckEvent> CanCollid = new Listener<>(event -> {
		event.cancel();
	});
}
