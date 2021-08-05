package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.RenderCameraEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "camera clips when in 3rd person.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@EventHandler
    private Listener<RenderCameraEvent> onRenderCameraEvent = new Listener<>(event -> {
        event.cancel();
    });

}
