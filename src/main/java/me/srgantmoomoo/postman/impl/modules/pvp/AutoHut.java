package me.srgantmoomoo.postman.impl.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "automatically builds hut for u.", Keyboard.KEY_NONE, Category.PVP);
	}

}
