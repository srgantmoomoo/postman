package me.srgantmoomoo.postman.impl.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class LongJump extends Module {
	
	public LongJump() {
		super ("longJump", "long, jump.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
