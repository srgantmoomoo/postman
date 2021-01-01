package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AimBot extends Module {
	
	public AimBot() {
		super ("aimBot", "locks camera on to the closest target", Keyboard.KEY_NONE, Category.PVP);
	}

}
