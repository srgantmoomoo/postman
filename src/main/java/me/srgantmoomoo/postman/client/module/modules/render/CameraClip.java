package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.RenderCameraEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "camera clips when in 3rd person.", Keyboard.KEY_NONE, Category.RENDER);
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
    private Listener<RenderCameraEvent> onRenderCameraEvent = new Listener<>(event -> {
        event.cancel();
    });

}
