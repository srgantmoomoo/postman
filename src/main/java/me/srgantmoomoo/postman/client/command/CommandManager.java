package me.srgantmoomoo.postman.client.command;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.postman.client.command.commands.Toggle;

public class CommandManager {
	
	public List<Command> commands = new ArrayList<Command>();
	
	public void setup() {
		commands.add(new Toggle());
	}
	
	public void handleChat()

}
