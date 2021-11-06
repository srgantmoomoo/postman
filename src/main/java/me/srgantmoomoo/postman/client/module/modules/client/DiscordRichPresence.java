package me.srgantmoomoo.postman.client.module.modules.client;

import me.srgantmoomoo.postman.api.util.misc.Discord;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import org.lwjgl.input.Keyboard;

public class DiscordRichPresence extends Module {
	
	public DiscordRichPresence() {
		super ("discordRpc", "shows ur playing postman on discord.", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
	@Override
	public void onEnable() {
	    Discord.startRPC();
	}
	
	@Override
	public void onDisable() {
	    Discord.stopRPC();
	}
}