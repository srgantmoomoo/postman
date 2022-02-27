package me.srgantmoomoo.postman.client.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumHand;

public class AutoUse extends Module {
	
	public AutoUse() {
		super("autoUse", "automatically uses whatever u r holding.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.currentScreen == null) KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
		else mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
	}
	
	@Override
	public void onDisable() {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	}
}