package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.RENDER);
	}

}
