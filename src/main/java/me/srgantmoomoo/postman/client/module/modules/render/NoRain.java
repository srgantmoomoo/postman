package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.RenderRainEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class NoRain extends Module {
	
	public NoRain() {
		super ("noRain", "eliminates rain.", Keyboard.KEY_NONE, Category.RENDER);
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
	private Listener<RenderRainEvent> onRain = new Listener<>(event -> {
	    if (mc.world == null)
	        return;
	    event.cancel();
	});
}
