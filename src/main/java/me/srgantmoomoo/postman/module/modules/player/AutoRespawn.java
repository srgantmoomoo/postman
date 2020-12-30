package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoRespawn extends Module {
	
	public AutoRespawn() {
		super("autoRespawn", "classic hud", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
