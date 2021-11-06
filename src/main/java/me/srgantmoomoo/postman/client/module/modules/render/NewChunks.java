package me.srgantmoomoo.postman.client.module.modules.render;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class NewChunks extends Module {
	
	public NewChunks() {
		super ("newChunks", "shows when new chunks r generated.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
