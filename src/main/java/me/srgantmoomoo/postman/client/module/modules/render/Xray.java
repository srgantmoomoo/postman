package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Xray extends Module {
	
	public Xray() {
		super ("xray", "use commands for better customizability.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
