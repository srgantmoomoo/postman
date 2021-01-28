package me.srgantmoomoo.postman.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

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
			boolean moduleFound = false;
			for(Module module : ModuleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					ModuleManager.addChatMessage(module.name + " " + (module.isToggled() ? ChatFormatting.GREEN + "enabled" + ChatFormatting.GRAY + "." : ChatFormatting.RED + "disabled" + ChatFormatting.GRAY + "."));
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				ModuleManager.addChatMessage(ChatFormatting.DARK_RED + "module not found.");
			}
		}else {
			ModuleManager.addChatMessage("correct usage of toggle command -> !toggle <module>");
		}
	}

}
