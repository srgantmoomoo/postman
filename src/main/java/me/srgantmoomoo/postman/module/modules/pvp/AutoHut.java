package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.PVP);
	}

}
