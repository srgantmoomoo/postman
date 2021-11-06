package me.srgantmoomoo.postman.client.module.modules.movement;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class LongJump extends Module {
	
	public LongJump() {
		super ("longJump", "long, jump.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
