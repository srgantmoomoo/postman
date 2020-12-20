package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Xray extends Module {
	
	public Xray() {
		super ("xray", "see thru some hot girls clothes lol", Keyboard.KEY_NONE, Category.RENDER);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
