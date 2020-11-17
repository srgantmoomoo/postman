package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class NewChunks extends Module {
	
	public NewChunks() {
		super ("newChunks", "shows when newchunks r generated", Keyboard.KEY_NONE, Category.RENDER);
	}

}
