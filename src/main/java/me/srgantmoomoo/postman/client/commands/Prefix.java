package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class Prefix extends Command {
	public Prefix() {
		super("prefix", "sets the command prefix.", "prefix <key>", "p");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			Main.INSTANCE.commandManager.setCommandPrefix(key);
			Main.INSTANCE.moduleManager.addChatMessage(String.format(ChatFormatting.GREEN + "command prefix " + ChatFormatting.GRAY + "was set to " + ChatFormatting.GREEN + Main.INSTANCE.commandManager.prefix));
		}
		
		if(args.length == 0) Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
	}

}