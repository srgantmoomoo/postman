package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraft.client.settings.KeyBinding;

public class AutoMine extends Module {
	
	public AutoMine() {
		super("autoMine", "automatically mines.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.currentScreen == null) KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), true);
		else mc.playerController.isHittingBlock = true;
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
	}
}