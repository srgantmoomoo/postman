package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Weather extends Module {
	
	public Weather() {
		super ("weather", "eliminates weather", Keyboard.KEY_NONE, Category.RENDER);
	}

}
