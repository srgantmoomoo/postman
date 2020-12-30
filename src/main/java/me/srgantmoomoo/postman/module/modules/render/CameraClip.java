package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.RENDER);
	}

}
