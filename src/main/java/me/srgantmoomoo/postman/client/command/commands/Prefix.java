package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Prefix extends Command {
	public Prefix() {
		super("Prefix", "Sets the command prefix.", "prefix <key>", "p");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			CommandManager.setCommandPrefix(key);
			ModuleManager.addChatMessage(String.format(ChatFormatting.GREEN + "prefix " + ChatFormatting.GRAY + "was set to " + ChatFormatting.GREEN + CommandManager.prefix));
		}
		
		if(args.length == 0) ModuleManager.addChatMessage("correct usage of bind command -> !bind <module> <key>");
	}

}