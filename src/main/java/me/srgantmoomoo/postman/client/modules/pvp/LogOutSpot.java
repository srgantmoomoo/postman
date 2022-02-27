package me.srgantmoomoo.postman.client.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class LogOutSpot extends Module {
	
	public LogOutSpot() {
		super ("logOutSpot", "shows where a player logs out.", Keyboard.KEY_NONE, Category.PVP);
	}

}
