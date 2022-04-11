package me.srgantmoomoo.postman.impl.modules.client;

import java.awt.Font;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;

public class ClientFont extends Module {
	public static final ClientFont INSTANCE = new ClientFont();

	public ModeSetting font = new ModeSetting("font", this, "Comic Sans Ms", "Comic Sans Ms", "Arial", "Verdana");
	
	private ClientFont() {
		super ("clientFont", "changes the font the client uses.", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(font);
	}
	
	@Override
	public void onEnable() {
		if(font.is("Comic Sans Ms")) {
			Main.INSTANCE.customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Arial")) {
			Main.INSTANCE.customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Verdana")) {
			Main.INSTANCE.customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
		}
	}
}