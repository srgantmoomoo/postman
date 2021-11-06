package me.srgantmoomoo.postman.client.module.modules.movement;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class Scaffold extends Module {
	
	public Scaffold() {
		super ("scaffold", "places blocks under u automatically.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
