package me.srgantmoomoo.postman.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.util.misc.Discord;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class DiscordRichPresence extends Module {
	
	public DiscordRichPresence() {
		super ("discordRp", "shows ur playing postman on discord", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
		public void onEnable() {
	    Discord.startRPC();
	}
	
	public void onDisable() {
	    Discord.stopRPC();
		}
	}
	