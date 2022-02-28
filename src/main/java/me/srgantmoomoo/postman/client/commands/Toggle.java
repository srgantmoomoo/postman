package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "toggles a module on or off.", "toggle <module>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			boolean moduleFound = false;
			for(Module module : Main.INSTANCE.moduleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					Main.INSTANCE.commandManager.sendClientChatMessage(module.name + " " + (module.isToggled() ? ChatFormatting.GREEN + "enabled" + ChatFormatting.GRAY + "." : ChatFormatting.RED + "disabled" + ChatFormatting.GRAY + "."), true);
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				Main.INSTANCE.commandManager.sendClientChatMessage(ChatFormatting.DARK_RED + "module not found.", true);
			}
		}else Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}
