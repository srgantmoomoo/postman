package me.srgantmoomoo.postman.client.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class AutoTrap  extends Module {
	
	public AutoTrap() {
		super ("autoTrap", "automatically traps opponent.", Keyboard.KEY_NONE, Category.PVP);
	}

}
