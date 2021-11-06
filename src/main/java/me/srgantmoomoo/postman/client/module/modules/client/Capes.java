package me.srgantmoomoo.postman.client.module.modules.client;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class Capes extends Module {
	
	public Capes() {
		super("capes", "allows u to see ur and others postman capes.", Keyboard.KEY_NONE, Category.CLIENT);
	}

}
