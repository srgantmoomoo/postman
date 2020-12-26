package me.srgantmoomoo.postman.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Scaffold extends Module {
	
	public Scaffold() {
		super ("scaffold", "places blocks under u automatically", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
