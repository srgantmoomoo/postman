package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoElytra extends Module {
	
	public AutoElytra() {
		super ("autoElytra", "automatically replaces a broken elytra.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
