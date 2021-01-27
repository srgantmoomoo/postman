package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.module.Module;

public class Toggle extends Command {
	
	public Toggle() {
		super("Toggle", "Toggles a module by name.", "toggle <name>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			
			boolean foundModule = false;
			
			for(Module module : Main.moduleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					foundModule = true;
					break;
				}
			}
		}
	}

}
