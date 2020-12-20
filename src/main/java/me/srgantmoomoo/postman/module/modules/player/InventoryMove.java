package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
public class InventoryMove extends Module {
	
	public InventoryMove() {
		super ("inventoryMove", "lets you move while in ur inventory", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();

	public void onUpdate(){
		if (mc.currentScreen != null){
			if (!(mc.currentScreen instanceof GuiChat)){
				if (Keyboard.isKeyDown(200)){
					mc.player.rotationPitch -= 5;
				}
				if (Keyboard.isKeyDown(208)){
					mc.player.rotationPitch += 5;
				}
				if (Keyboard.isKeyDown(205)){
					mc.player.rotationYaw += 5;
				}
				if (Keyboard.isKeyDown(203)){
					mc.player.rotationYaw -= 5;
				}
				if (mc.player.rotationPitch > 90){
					mc.player.rotationPitch = 90;
				}
				if (mc.player.rotationPitch < -90){
					mc.player.rotationPitch = -90;
				}
			}
		}
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}