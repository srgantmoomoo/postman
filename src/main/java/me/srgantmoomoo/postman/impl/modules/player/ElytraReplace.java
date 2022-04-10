package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class ElytraReplace extends Module {
	public ElytraReplace() {
		super ("elytraReplace", "automatically replaces a broken elytra.", Keyboard.KEY_NONE, Category.PLAYER);
	}
}
