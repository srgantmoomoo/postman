package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class NoHurtCam extends Module {
	
	public NoHurtCam() {
		super ("noHurtCam", "hurt animation isnt rendered", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onUpdate() {
		mc.player.performHurtAnimation();
	}

}	
	
