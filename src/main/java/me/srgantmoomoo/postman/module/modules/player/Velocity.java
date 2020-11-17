package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;

public class Velocity extends Module {
	public NumberSetting percent = new NumberSetting("percent", 0, 0, 100, 10);
	
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
		if(mc.player.hurtTime == mc.player.maxHurtTime && mc.player.maxHurtTime > 0) {
			mc.player.motionX *= (float) percent.getValue() / 100;
			mc.player.motionY *= (float) percent.getValue() / 100;
			mc.player.motionZ *= (float) percent.getValue() / 100;
		}
		}

}
