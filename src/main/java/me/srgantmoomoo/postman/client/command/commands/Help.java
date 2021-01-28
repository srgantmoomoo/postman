package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Help extends Command {
	
	public Help() {
		super("help", "helps lol.", "bind <name> <key> | clear", "h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		ModuleManager.addChatMessage(Reference.NAME + " " + Reference.VERSION);
	}

}
