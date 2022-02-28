package me.srgantmoomoo.postman.impl.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.client.settings.KeyBinding;

public class AutoWalk extends Module {
	
	public AutoWalk() {
		super ("autoWalk", "automatically walks for u, u lazy fuck.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
	}
	
	@Override
	public void onUpdate() {
		if(mc.currentScreen == null) {
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
		}
	}

}
