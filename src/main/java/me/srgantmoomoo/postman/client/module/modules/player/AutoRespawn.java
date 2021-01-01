package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoRespawn extends Module {
	
	public AutoRespawn() {
		super("autoRespawn", "classic hud", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
