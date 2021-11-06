package me.srgantmoomoo.postman.client.module.modules.client;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class MainMenuWatermark extends Module {
	
	public MainMenuWatermark() {
		super("mainMenuWatermark", "shows postman on minecrafts main menu screen.", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
	}
	//check MixinGuiMainMenu :)

}
