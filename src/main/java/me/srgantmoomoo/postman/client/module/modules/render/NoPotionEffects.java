package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class NoPotionEffects extends Module {
	
	public NoPotionEffects() {
		super ("noPotionEffects", "yea,no... fall", Keyboard.KEY_NONE, Category.RENDER);
	}

}
