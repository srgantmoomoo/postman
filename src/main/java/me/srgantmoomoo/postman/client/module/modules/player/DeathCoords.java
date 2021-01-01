package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class DeathCoords extends Module {
	
	public DeathCoords() {
		super ("deathCoords", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
