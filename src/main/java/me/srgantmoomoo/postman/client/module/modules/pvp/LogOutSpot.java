package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class LogOutSpot extends Module {
	
	public LogOutSpot() {
		super ("logOutSpot", "shows where a player logs out.", Keyboard.KEY_NONE, Category.PVP);
	}

}
