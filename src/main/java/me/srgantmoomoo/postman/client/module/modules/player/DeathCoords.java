package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class DeathCoords extends Module {
	
	public DeathCoords() {
		super ("deathCoords", "draws esp around storage blocks", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onUpdate() {
		if(mc.player.isDead) {
			ModuleManager.addChatMessage(ChatFormatting.WHITE + "lol u just died loser" + 
					ChatFormatting.GRAY + " (x)" + mc.player.getPosition().x + " (y)" + mc.player.getPosition().y + " (z)" + mc.player.getPosition().z);
			toggled = false;
		}
	}

}
