package me.srgantmoomoo.postman.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class MainMenuInfo extends Module {
	
	public MainMenuInfo() {
		super("mainMenuInfo", "asd dsa", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = false;
	}
	//check MixinGuiMainMenu :)

}
