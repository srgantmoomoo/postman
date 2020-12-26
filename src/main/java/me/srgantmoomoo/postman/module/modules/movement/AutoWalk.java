package me.srgantmoomoo.postman.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoWalk extends Module {
	
	public AutoWalk() {
		super ("autoWalk", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
