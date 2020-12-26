package me.srgantmoomoo.postman.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Step extends Module {
	
	public Step() {
		super ("step", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
