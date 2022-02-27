package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import net.minecraft.util.text.TextFormatting;

public class Help extends Command {
	public static Prefix prefix;
	public static Toggle toggle;
	public static Bind bind;
	public static Baritone baritone;
	public static Friend friend;
	public static AutoCope autoCope;
	public static Protester protester;
	public static MobOwner mobOwner;
	public static Clip clip;
	public static Vanish vanish;
	
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
		String PREFIX = CommandManager.prefix;
		
		prefix = new Prefix();
		toggle = new Toggle();
		bind = new Bind();
		baritone = new Baritone();
		friend = new Friend();
		autoCope = new AutoCope();
		protester = new Protester();
		mobOwner = new MobOwner();
		clip = new Clip();
		vanish = new Vanish();

		Main.INSTANCE.moduleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");

		Main.INSTANCE.moduleManager.addChatMessage(ChatFormatting.BOLD + Reference.NAME + " " + Reference.VERSION + "!");

		helpMessage(prefix.name, prefix.description, prefix.syntax);
		helpMessage(toggle.name, toggle.description, toggle.syntax);
		helpMessage(bind.name, bind.description, bind.syntax);
		helpMessage(baritone.name, baritone.description, baritone.syntax);
		helpMessage(friend.name, friend.description, friend.syntax);
		helpMessage(autoCope.name, autoCope.description, autoCope.syntax);
		helpMessage(protester.name, protester.description, protester.syntax);
		helpMessage(mobOwner.name, mobOwner.description, mobOwner.syntax);
		helpMessage(clip.name, clip.description, clip.syntax);
		helpMessage(vanish.name, vanish.description, vanish.syntax);

		Main.INSTANCE.moduleManager.addChatMessage(ChatFormatting.GREEN + "-------------------");

	}
	
	private void helpMessage(String name, String desc, String syntax) {
		Main.INSTANCE.moduleManager.addChatMessage(WHITE + name + GRAY + " - " + desc + RED + ITALIC + " [ " + syntax + " ]");
    }

}