package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.util.misc.Discord;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class DiscordRichPresence extends Module{
	
	public DiscordRichPresence() {
		super ("discordRp", "shows ur playing postman on discord", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
		public void onEnable(){
	    Discord.startRPC();
	}
	
	public void onDisable(){
	    Discord.stopRPC();
		}
	}
	