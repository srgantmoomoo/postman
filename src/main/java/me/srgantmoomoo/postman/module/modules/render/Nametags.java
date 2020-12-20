package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class Nametags extends Module {
	
	public Nametags() {
		super ("nametags", "gives more info on a persons nametag", Keyboard.KEY_NONE, Category.RENDER);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}
