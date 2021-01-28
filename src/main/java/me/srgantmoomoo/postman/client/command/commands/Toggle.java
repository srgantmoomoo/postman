package me.srgantmoomoo.postman.client.command.commands;

import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;

public class Toggle extends Command {
	
	public Toggle() {
		super("Toggle", "Toggles a module by name.", "toggle <name>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			boolean foundModule = false;
			for(Module module : ModuleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					ModuleManager.addChatMessage((module.isToggled() ? "enabled" : "disabled") + " " + module.name);
					foundModule = true;
					break;
				}
			}
			if(!foundModule) {
				ModuleManager.addChatMessage("module not found.");
			}
		}
	}

}
