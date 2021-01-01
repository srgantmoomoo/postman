package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.settings.NumberSetting;
import net.minecraft.client.Minecraft;

public class Velocity extends Module {
	public NumberSetting percent = new NumberSetting("percent", this, 0, 0, 100, 10);
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public boolean on;
	
	public Velocity() {
		super ("velocity", "take no knockback when hit", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(percent);
	}
	
	@Override
	public void onUpdate() {
			if(mc.player == null) {
				return;
			}
		if(mc.player.hurtTime > 0) {
			mc.player.motionX *= (float) percent.getValue() / 100;
			mc.player.motionY *= (float) percent.getValue() / 100;
			mc.player.motionZ *= (float) percent.getValue() / 100;
		}
		}

}
