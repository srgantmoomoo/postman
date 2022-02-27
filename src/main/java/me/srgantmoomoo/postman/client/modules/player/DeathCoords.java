package me.srgantmoomoo.postman.client.modules.player;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class DeathCoords extends Module {
	
	public DeathCoords() {
		super ("deathCoords", "tells u ur coords after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.player.isDead) {
			ModuleManager.addChatMessage(ChatFormatting.WHITE + "lol u just died loser" + 
					ChatFormatting.GRAY + " (x)" + mc.player.getPosition().x + " (y)" + mc.player.getPosition().y + " (z)" + mc.player.getPosition().z);
			toggled = false;
		}
	}

}