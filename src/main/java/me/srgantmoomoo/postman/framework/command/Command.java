package me.srgantmoomoo.postman.framework.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
	public String name, description, syntax;
	
	public final List<String> aliases = new ArrayList<String>();
	
	public Command(String name, String description, String syntax, String... aliases) {
		this.name = name;
		this.description = description;
		this.syntax = syntax;
		this.aliases = Arrays.asList(aliases);
	}
	
	public abstract void onCommand(String[] args, String command);
	
	public String getName() {
		return this.name;
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
		return this.aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
