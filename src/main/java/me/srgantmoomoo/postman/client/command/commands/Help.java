package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.util.text.TextFormatting;

public class Help extends Command {
	
	public Help() {
		super("help", "helps lol.", "help", "h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		ModuleManager.addChatMessage(ChatFormatting.BOLD + Reference.NAME + " " + Reference.VERSION + "!");

		ModuleManager.addChatMessage("prefix - " + TextFormatting.ITALIC + "sets the command prefix. -> "  + CommandManager.prefix + "prefix <key>");
		
		ModuleManager.addChatMessage("toggle - " + TextFormatting.ITALIC + "toggles a module on or off. -> "  + CommandManager.prefix + "toggle <module>");
		
		ModuleManager.addChatMessage("bind - " + TextFormatting.ITALIC + "bind modules to specific keys. -> " + CommandManager.prefix + "bind <name> <key> | " + CommandManager.prefix + "bind clear");
		
		ModuleManager.addChatMessage("friend - " + TextFormatting.ITALIC + "manage your friends. -> " + CommandManager.prefix + "friend list | " + CommandManager.prefix + "friend add <name> | " 
				+ CommandManager.prefix + "friend remove <name> | " + CommandManager.prefix + "friend clear");
		
		ModuleManager.addChatMessage("clip - " + TextFormatting.ITALIC + "clip horrizontally or vertically through blocks. -> " + CommandManager.prefix + "clip h <blocks> | " + CommandManager.prefix 
				+ "clip v <blocks>");
	}

}