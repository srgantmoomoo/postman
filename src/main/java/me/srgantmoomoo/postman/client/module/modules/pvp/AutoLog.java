package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoLog extends Module{
	
	public AutoLog() {
		super ("autoLog", "logs out when ur too low", Keyboard.KEY_NONE, Category.PVP);
	}

}

