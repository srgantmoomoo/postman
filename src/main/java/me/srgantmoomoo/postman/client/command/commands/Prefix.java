package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;

public class Prefix extends Command {
	
	public Prefix() {
		super("Prefix", "Sets the command prefix.", "prefix <key>", "p");
	}
	
	@Override
	public void onCommand(String[] args, String command) {

	}

}
