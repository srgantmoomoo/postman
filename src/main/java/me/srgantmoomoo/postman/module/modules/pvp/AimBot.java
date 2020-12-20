package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AimBot extends Module {
	
	public AimBot() {
		super ("aimBot", "locks camera on to the closest target", Keyboard.KEY_NONE, Category.PVP);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
