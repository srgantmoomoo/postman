package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoCrystal extends Module {
	public AutoCrystal() {
		super ("autoCrystal", "automatically crystals ur opponent in the best way possible", Keyboard.KEY_NONE, Category.PVP);
	}
}	

