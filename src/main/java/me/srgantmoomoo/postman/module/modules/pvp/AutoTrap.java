package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoTrap  extends Module {
	
	public AutoTrap() {
		super ("autoTrap", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.PVP);
	}

}
