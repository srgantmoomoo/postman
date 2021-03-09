package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.friend.FriendManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Friend extends Command {
	
    public Friend() {
		super("friend", "friend ppl yea yea.", "friend list | add <name> | del <name> | clear", "f");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String main = args[0];
			boolean friendFound = false;
			boolean commandFound = false;
			
			if(main.equalsIgnoreCase("list")) {
				ModuleManager.addChatMessage("friends: " + FriendManager.getFriendsByName());
			}
			
			if (main.equalsIgnoreCase("add") && !FriendManager.isFriend(args[1])) {
				FriendManager.addFriend(args[1]);
				ModuleManager.addChatMessage("added friend: " + args[1].toUpperCase());
			}
			if (main.equalsIgnoreCase("remove") && FriendManager.isFriend(args[1])) {
				FriendManager.delFriend(args[1]);
				ModuleManager.addChatMessage("removed friend: " + args[1].toUpperCase());	
			}
		}else  ModuleManager.addChatMessage("penis -> " + CommandManager.prefix + "toggle <module>");
	}
}