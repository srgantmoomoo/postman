package me.srgantmoomoo.postman.framework.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
	public String name, description, syntax;
	public List<String> aliases = new ArrayList<String>();

	protected static final Minecraft mc = Minecraft.getMinecraft();
	protected static final ChatFormatting WHITE = ChatFormatting.WHITE;
	protected static final ChatFormatting GRAY = ChatFormatting.GRAY;
	protected static final ChatFormatting RED = ChatFormatting.RED;
	protected static final ChatFormatting GREEN = ChatFormatting.GREEN;
	protected static final ChatFormatting AQUA = ChatFormatting.AQUA;
	
	public Command(String name, String description, String syntax, String... aliases) {
		this.name = name;
		this.description = description;
		this.syntax = syntax;
		this.aliases = Arrays.asList(aliases);
	}
	
	public abstract void onCommand(String[] args, String command);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
