package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "automatically builds hut for u.", Keyboard.KEY_NONE, Category.PVP);
	}

}
