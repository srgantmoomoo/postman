package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class ElytraReplace extends Module {
	
	public ElytraReplace() {
		super ("elytraReplace", "automatically replaces a broken elytra.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
