package me.srgantmoomoo.postman.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class MainMenuInfo extends Module {
	
	public MainMenuInfo() {
		super("mainMenuInfo", "shows postman on minecrafts main menu screen.", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
	}
	//check MixinGuiMainMenu :)

}
