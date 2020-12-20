package me.srgantmoomoo.postman.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class AutoLog extends Module{
	
	public AutoLog() {
		super ("autoLog", "logs out when ur too low", Keyboard.KEY_NONE, Category.PVP);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}

