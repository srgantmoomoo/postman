package me.srgantmoomoo.postman.module.modules.hud;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;

public class TacoWendsday extends Module {
	private Minecraft mc = Minecraft.getMinecraft();

	
	public TacoWendsday() {
		super ("tacoWendsday", "faggot", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
	public void onEnable() {
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
