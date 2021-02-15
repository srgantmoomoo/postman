package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class NoHurtCam extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "sikeLol");
	
	public NoHurtCam() {
		super ("noHurtCam", "hurt animation isnt rendered.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(mode);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onUpdate() {
		if(mode.is("sikeLol")) {
		mc.player.performHurtAnimation();
		}
	}

}	
