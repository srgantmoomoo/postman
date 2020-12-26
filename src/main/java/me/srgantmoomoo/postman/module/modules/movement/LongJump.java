package me.srgantmoomoo.postman.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class LongJump extends Module {
	
	public LongJump() {
		super ("longJump", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
