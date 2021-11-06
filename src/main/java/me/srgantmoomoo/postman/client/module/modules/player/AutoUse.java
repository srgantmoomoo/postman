package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;

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