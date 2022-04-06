package me.srgantmoomoo.postman.impl.modules.client;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.util.Discord;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;

public class DiscordRichPresence extends Module {
	
	public DiscordRichPresence() {
		super ("discordRpc", "shows ur playing postman on discord.", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
	@Override
	public void onEnable() {
	    Discord.startRPC();
		Main.INSTANCE.commandManager.sendClientChatMessage("starting discordRPC... :)", true);
	}
	
	@Override
	public void onDisable() {
	    Discord.stopRPC();
		Main.INSTANCE.commandManager.sendClientChatMessage("stopping discordRPC... :(", true);
	}

}