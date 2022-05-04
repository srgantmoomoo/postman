package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Xray extends Module {
	public Xray() {
		super ("xray", "use commands for better customizability.", Keyboard.KEY_NONE, Category.RENDER);
	}
}
