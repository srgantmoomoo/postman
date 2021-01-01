package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class LongJump extends Module {
	
	public LongJump() {
		super ("longJump", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
