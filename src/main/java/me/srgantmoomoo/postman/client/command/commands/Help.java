package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.util.text.TextFormatting;

public class Help extends Command {
	
	public Help() {
		super("help", "helps lol.", "bind <name> <key> | clear", "h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		ModuleManager.addChatMessage(ChatFormatting.BOLD + Reference.NAME + " " + Reference.VERSION + "!");
		ModuleManager.addChatMessage("prefix - " + TextFormatting.ITALIC + "allows you to change the command prefix" + " -> "  + CommandManager.prefix + "prefix <key>");
		ModuleManager.addChatMessage("toggle - " + TextFormatting.ITALIC + "toggles modules on and off" + " -> "  + CommandManager.prefix + "toggle <module>");
		ModuleManager.addChatMessage("bind - " + TextFormatting.ITALIC + "allows you to set keybinds" + " -> " + CommandManager.prefix + "bind <module> <key>");
	}

}
