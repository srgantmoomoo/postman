package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Speed extends Module {
	
	public Speed() {
		super ("speed", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
