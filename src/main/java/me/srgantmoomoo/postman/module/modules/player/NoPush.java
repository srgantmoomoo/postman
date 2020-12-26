package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;

public class NoPush extends Module {
	
	public float saveReduction = 1.0E8F;
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public NoPush() {
		super ("noPush", "u cant get pushed, and u cant push", Keyboard.KEY_NONE, Category.PLAYER);
	}

	public void onUpdate() {
		if (this.saveReduction == 1.0E8F)
		      this.saveReduction = mc.player.entityCollisionReduction; 
		    mc.player.entityCollisionReduction = 1.0F;
	}
	
	public void onEnable() {
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
}