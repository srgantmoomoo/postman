package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class FullBright extends Module {
	
	public FullBright() {
		super ("fullBright", "makes everything fully bright", Keyboard.KEY_NONE, Category.RENDER);
	}
	float oldGamma;
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void onEnable() {
		oldGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 420;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = oldGamma;
	}
}
