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
		String PREFIX = CommandManager.prefix;
		
		ModuleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");
		
		ModuleManager.addChatMessage(ChatFormatting.BOLD + Reference.NAME + " " + Reference.VERSION + "!");

		ModuleManager.addChatMessage("prefix (p) - " + TextFormatting.ITALIC + "sets the command prefix. -> "  + PREFIX + "prefix <key>");
		
		ModuleManager.addChatMessage("toggle (t) - " + TextFormatting.ITALIC + "toggles a module on or off. -> "  + PREFIX + "toggle <module>");
		
		ModuleManager.addChatMessage("bind (bi) - " + TextFormatting.ITALIC + "bind modules to specific keys. -> " + PREFIX + "bind <name> <key> | " + PREFIX + "bind clear");
		
		ModuleManager.addChatMessage("baritone (b) - " + TextFormatting.ITALIC + "use baritone api commands. - > "  + PREFIX + "baritone stop | " + PREFIX + 
				"baritone goto <x> <z> | " + PREFIX + "baritone mine <block> | " + PREFIX + "baritone farm");
		
		ModuleManager.addChatMessage("friend (f) - " + TextFormatting.ITALIC + "manage your friends. -> " + PREFIX + "friend list | " + PREFIX + 
				"friend add <name> | " + PREFIX + "friend remove <name> | " + PREFIX + "friend clear");
		
		ModuleManager.addChatMessage("autoCope (ac) - " + TextFormatting.ITALIC + "edit the autoCope msg. - > "  + PREFIX + "autoCope <msg>");
		
		ModuleManager.addChatMessage("protester (pr) - " + TextFormatting.ITALIC + "edit the protester msg. - > "  + PREFIX + "protester <msg>");
		
		ModuleManager.addChatMessage("mobOwner (mo) - " + TextFormatting.ITALIC + "check the owner of a ridden mob. -> " + PREFIX + "mobOwner"); // fixed lol
		
		ModuleManager.addChatMessage("clip (c) - " + TextFormatting.ITALIC + "clip horrizontally or vertically through blocks. -> " + PREFIX + "clip h <blocks> | " + PREFIX 
				+ "clip v <blocks>");
		
		ModuleManager.addChatMessage("vanish (v) - " + TextFormatting.ITALIC + "vanish ridden entities. - > " + PREFIX + "vanish");
		
		ModuleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");

	}

}