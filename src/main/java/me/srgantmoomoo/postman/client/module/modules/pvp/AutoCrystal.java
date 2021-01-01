package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoCrystal extends Module {
	
	public AutoCrystal() {
		super ("autoCrystal", "automatically crystals ur opponent in the best way possible", Keyboard.KEY_NONE, Category.PVP);
	}
}	

