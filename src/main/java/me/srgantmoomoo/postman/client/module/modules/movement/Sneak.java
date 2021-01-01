package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Sneak extends Module {
	
	public Sneak() {
		super ("sneak", "s", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
