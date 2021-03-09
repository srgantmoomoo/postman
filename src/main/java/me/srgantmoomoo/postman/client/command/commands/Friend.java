package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.friend.FriendManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Friend extends Command {
	
    public Friend() {
		super("friend", "friend ppl yea yea.", "friend list | add <name> | remove <name> | clear", "f");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String start = args[0];
			
			if(start.equalsIgnoreCase("list")) {
				ModuleManager.addChatMessage("friends: " + FriendManager.getFriendsByName());
			}else 
			
			if (start.equalsIgnoreCase("add") && !FriendManager.isFriend(args[1])) {
				FriendManager.addFriend(args[1]);
				ModuleManager.addChatMessage("added friend: " + args[1].toUpperCase());
			}else
			if (start.equalsIgnoreCase("remove") && FriendManager.isFriend(args[1])) {
				FriendManager.removeFriend(args[1]);
				ModuleManager.addChatMessage("removed friend: " + args[1].toUpperCase());	
			}else {
				ModuleManager.addChatMessage("correct usage of friend command -> " + CommandManager.prefix + "friend add <name> / or " + CommandManager.prefix + "friend remove <name> / or " + CommandManager.prefix + "friend list");
			}
		}else ModuleManager.addChatMessage("correct usage of friend command -> " + CommandManager.prefix + "friend add <name> / or " + CommandManager.prefix + "friend remove <name> / or " + CommandManager.prefix + "friend list");
	}
}