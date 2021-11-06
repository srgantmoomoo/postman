package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class Multitask extends Module {
	
	public Multitask() {
		super("multitask", "allows you to do different things in each hand.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
