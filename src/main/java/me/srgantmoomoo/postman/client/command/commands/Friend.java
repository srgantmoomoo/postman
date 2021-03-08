package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;

public class Friend extends Command {
	
    public Friend() {
		super("friend", "friend ppl yea yea.", "friend list | add <name> | del <name> | clear", "f");
	}

	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		
	}
}