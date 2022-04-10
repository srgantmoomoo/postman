package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
public class GuiMove extends Module {
	public GuiMove() {
		super ("guiMove", "lets you move while in ur a gui screen.", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@Override
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
}
