package me.srgantmoomoo.postman.impl.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class AimBot extends Module {
	public AimBot() {
		super ("aimBot", "locks camera on to the closest target.", Keyboard.KEY_NONE, Category.PVP);
	}
}
