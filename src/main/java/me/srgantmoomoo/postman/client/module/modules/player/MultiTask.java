package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class MultiTask extends Module {
	
	public MultiTask() {
		super("multiTask", "allows you to do multiple things with each hand.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
