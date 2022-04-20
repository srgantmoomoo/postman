package me.srgantmoomoo.postman.impl.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;

public class Prefix extends Command {
	public Prefix() {
		super("prefix", "sets the command prefix.", "prefix <key>", "p");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			Main.INSTANCE.commandManager.setCommandPrefix(key);
			Main.INSTANCE.commandManager.sendClientChatMessage("command prefix set to " + WHITE + Main.INSTANCE.commandManager.prefix + GRAY + ".", true);
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}