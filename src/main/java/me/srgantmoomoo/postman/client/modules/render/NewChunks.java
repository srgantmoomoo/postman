package me.srgantmoomoo.postman.client.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class NewChunks extends Module {
	
	public NewChunks() {
		super ("newChunks", "shows when new chunks r generated.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
