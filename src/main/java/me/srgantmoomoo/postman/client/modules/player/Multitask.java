package me.srgantmoomoo.postman.client.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class Multitask extends Module {
	
	public Multitask() {
		super("multitask", "allows you to do different things in each hand.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
