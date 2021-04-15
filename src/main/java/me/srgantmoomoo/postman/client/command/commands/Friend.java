package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.friend.FriendManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Friend extends Command {
	
    public Friend() {
		super("friend", "manage your friends.", "friend list | friend add <name> | friend remove <name> | friend clear", "f");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String start = args[0];
			
			if(start.equalsIgnoreCase("list")) {
				ModuleManager.addChatMessage("friends: " + FriendManager.getFriendsByName());
			}else if(start.equalsIgnoreCase("clear")) {
				FriendManager.clearFriends();
				ModuleManager.addChatMessage("cleared all friends");
			}else
				
				if (start.equalsIgnoreCase("add") && !FriendManager.isFriend(args[1])) {
					FriendManager.addFriend(args[1]);
					ModuleManager.addChatMessage(ChatFormatting.GREEN + "added" + ChatFormatting.GRAY + " friend: " + args[1].toUpperCase());
				}else if (start.equalsIgnoreCase("remove") && FriendManager.isFriend(args[1])) {
					FriendManager.removeFriend(args[1]);
					ModuleManager.addChatMessage(ChatFormatting.DARK_RED + "removed" + ChatFormatting.GRAY + " friend: " + args[1].toUpperCase());	
				}else {
					CommandManager.correctUsageMsg(getName(), getSyntax());
				}
		}else CommandManager.correctUsageMsg(getName(), getSyntax());
	}
}