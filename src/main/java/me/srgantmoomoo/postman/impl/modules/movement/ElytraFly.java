package me.srgantmoomoo.postman.impl.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class ElytraFly extends Module {
	public ElytraFly() {
		super ("elytraFly", "fly ez lololol", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}

