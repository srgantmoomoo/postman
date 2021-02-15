package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class ChatBot extends Module {
	
	public ChatBot() {
		super ("chatBot", "bot chat.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	

}
