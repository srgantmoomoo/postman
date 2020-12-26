package me.srgantmoomoo.postman.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Speed extends Module {
	
	public Speed() {
		super ("speed", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
