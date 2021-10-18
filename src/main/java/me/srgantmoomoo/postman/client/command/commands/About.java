package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class About extends Command {
	
    public About() {
		super("about", "tells you more about the client, "abt");
	}
ModuleManager.addChatMessage("This is a filler for the time being");

	}
}
