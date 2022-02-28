package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class DeathCoords extends Module {
	
	public DeathCoords() {
		super ("deathCoords", "tells u ur coords after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.player.isDead) {
			Main.INSTANCE.commandManager.sendClientChatMessage(ChatFormatting.WHITE + "lol u just died loser" +
					ChatFormatting.GRAY + " (x)" + mc.player.getPosition().x + " (y)" + mc.player.getPosition().y + " (z)" + mc.player.getPosition().z, true);
			toggled = false;
		}
	}

}