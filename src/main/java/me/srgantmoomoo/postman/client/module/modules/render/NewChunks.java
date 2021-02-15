package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class NewChunks extends Module {
	
	public NewChunks() {
		super ("newChunks", "shows when new chunks r generated.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
