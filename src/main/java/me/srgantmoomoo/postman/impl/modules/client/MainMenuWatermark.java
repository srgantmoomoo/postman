package me.srgantmoomoo.postman.impl.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class MainMenuWatermark extends Module {
	
	public MainMenuWatermark() {
		super("mainMenuWatermark", "shows postman on minecrafts main menu screen.", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
	}
	//check MixinGuiMainMenu :)

}
