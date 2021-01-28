package me.srgantmoomoo.postman.client.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.command.commands.Toggle;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.ClientChatEvent;

public class CommandManager {
	
	public List<Command> commands = new ArrayList<Command>();
	public String prefix = ".";
	
	public CommandManager() {
		Main.EVENT_BUS.subscribe(this);
		register();
	}
	
	public void register() {
		commands.add(new Toggle());
	}
	
	@EventHandler
    public Listener<ClientChatEvent> listener = new Listener<>(event -> {
        String message = event.getMessage();
        if(!message.startsWith(prefix))
        	return;
        
        event.setCanceled(true);
        message = message.substring(prefix.length());
        if(message.split(" ").length > 0) {
        	String commandName = message.split(" ")[0];
        	for(Command c : commands) {
        		if(c.aliases.contains(commandName)) {
        		c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
        		}
        	}
        }
    });
}
