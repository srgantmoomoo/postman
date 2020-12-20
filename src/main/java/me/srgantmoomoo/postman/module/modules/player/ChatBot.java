package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class ChatBot extends Module {
	
	public ChatBot() {
		super ("chatBot", "bot chat", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
