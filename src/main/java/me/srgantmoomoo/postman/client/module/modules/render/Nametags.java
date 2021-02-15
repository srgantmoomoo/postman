package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Nametags extends Module {
	
	public Nametags() {
		super ("nametags", "gives more info on a persons nametag.", Keyboard.KEY_NONE, Category.RENDER);
	}
}
