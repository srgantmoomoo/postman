package me.srgantmoomoo.postman.client.module.modules.hud;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraft.client.Minecraft;

public class TacoWendsday extends Module {
	private Minecraft mc = Minecraft.getMinecraft();

	
	public TacoWendsday() {
		super ("tacoWendsday", "faggot", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
	public void onEnable() {
		
	}

}
