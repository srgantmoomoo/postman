package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import net.minecraft.util.text.TextFormatting;

public class Help extends Command {
	
	public Help() {
		super("help", "helps lol.", "help", "h");
	}

	TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
	TextFormatting WHITE = TextFormatting.WHITE;
    TextFormatting GRAY = TextFormatting.GRAY;
    TextFormatting AQUA = TextFormatting.AQUA;
    TextFormatting BOLD = TextFormatting.BOLD;
    TextFormatting ITALIC = TextFormatting.ITALIC;
    TextFormatting RED = TextFormatting.RED;

	@Override
	public void onCommand(String[] args, String command) {
		
		ModuleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");
		
		ModuleManager.addChatMessage(ChatFormatting.BOLD + Reference.NAME + " " + Reference.VERSION + "!");

		Main.commandManager.commands.forEach(c -> {
			helpMessage(c.name, c.description, c.syntax);
		});

		ModuleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");

	}
	
	private void helpMessage(String name, String desc, String syntax) {
        ModuleManager.addChatMessage(WHITE + name + GRAY + " - " + desc + RED + ITALIC + " [ " + syntax + " ]");
    }

}