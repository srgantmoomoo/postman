package me.srgantmoomoo.postman.client.module.modules.client;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ClientFont extends Module {
	public ModeSetting font = new ModeSetting("font", this, "Comic Sans Ms", "Comic Sans Ms", "Arial", "Verdana");
	
	public ClientFont() {
		super ("clientFont", "changes the font the client uses.", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(font);
	}
	
	@Override
	public void onEnable() {
		if(font.is("Comic Sans Ms")) {
			Main.customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Arial")) {
			Main.customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Verdana")) {
			Main.customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
		}
	}
}