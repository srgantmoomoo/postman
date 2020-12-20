package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Scaffold extends Module {
	
	public Scaffold() {
		super ("scaffold", "places blocks under u automatically", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
