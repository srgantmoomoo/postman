/*package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
public class InventoryMove extends Module {
	
	public InventoryMove() {
		super ("inventoryMove", "lets you move while in ur inventory", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void onUpdate() {
		KeyBinding[] moveKeys = new KeyBinding[] { mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump };
	KeyBinding[] arrayofKeyBinding = moveKeys;
	int i;
	int j;
	KeyBinding bind;
	
	if(!(mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof GuiIngameMenu))) {
		if(mc.currentScreen == null) {
			j = moveKeys.length;
			
			for(i = 0; i < j; i++) {
				bind = arrayofKeyBinding[i];
				if(!Keyboard.isKeyDown(bind.getKeyCode())) {
					KeyBinding.setKeyBindState(bind.getKeyCode(), false);
				}
			}
		}
	}else {
		if(mc.currentScreen instanceof GuiChat) {
			return;
		}
		arrayofKeyBinding = moveKeys;
		j = moveKeys.length;
				
				for(i = 0; i < j; ++i) {
					bind = arrayofKeyBinding[i];
					bind.isPressed() = Keyboard.isKeyDown(bind.getKeyCode());
				}
		mc.player.setSprinting(false);
	}

}*/