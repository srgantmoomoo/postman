package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumHand;

public class AutoWalk extends Module {
	
	public AutoWalk() {
		super ("autoWalk", "automatically walks for u, u lazy fuck.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
	}
	
	public void onUpdate() {
		if(mc.currentScreen == null) {
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
		}
	}

}
