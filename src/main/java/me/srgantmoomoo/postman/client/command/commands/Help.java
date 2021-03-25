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

		ModuleManager.addChatMessage("prefix (p) - " + TextFormatting.ITALIC + "sets the command prefix. -> "  + CommandManager.prefix + "prefix <key>");
		
		ModuleManager.addChatMessage("toggle (t) - " + TextFormatting.ITALIC + "toggles a module on or off. -> "  + CommandManager.prefix + "toggle <module>");
		
		ModuleManager.addChatMessage("bind (b) - " + TextFormatting.ITALIC + "bind modules to specific keys. -> " + CommandManager.prefix + "bind <name> <key> | " + CommandManager.prefix + "bind clear");
		
		ModuleManager.addChatMessage("friend (f) - " + TextFormatting.ITALIC + "manage your friends. -> " + CommandManager.prefix + "friend list | " + CommandManager.prefix + "friend add <name> | " 
				+ CommandManager.prefix + "friend remove <name> | " + CommandManager.prefix + "friend clear");
		
		ModuleManager.addChatMessage(TextFormatting.DARK_RED + "(wip) " + TextFormatting.GRAY + "mobOwner (mo) - " + TextFormatting.ITALIC + "check the owner of a ridden mob. -> " + CommandManager.prefix + "mobOwner"); // broken atm if null.
		
		ModuleManager.addChatMessage("clip (c) - " + TextFormatting.ITALIC + "clip horrizontally or vertically through blocks. -> " + CommandManager.prefix + "clip h <blocks> | " + CommandManager.prefix 
				+ "clip v <blocks>");
		
		ModuleManager.addChatMessage("vanish (v) - " + TextFormatting.ITALIC + "vanish ridden entities. - > " + CommandManager.prefix + "vanish");

	}

}