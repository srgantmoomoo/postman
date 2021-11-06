package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class AutoTrap  extends Module {
	
	public AutoTrap() {
		super ("autoTrap", "automatically traps opponent.", Keyboard.KEY_NONE, Category.PVP);
	}

}
