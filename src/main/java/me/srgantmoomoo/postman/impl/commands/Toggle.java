package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.impl.modules.client.NotificationModule;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "toggles a module on or off.", "toggle <module>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String moduleName = args[0];
			boolean moduleFound = false;
			for(Module module : Main.INSTANCE.moduleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					if(!Main.INSTANCE.moduleManager.getModuleByName("notification").isToggled() || !NotificationModule.INSTANCE.toggles.isEnabled()) {
						Main.INSTANCE.commandManager.sendClientChatMessage(module.name + " " + (module.isToggled() ? GREEN + "enabled" + GRAY + "." : RED + "disabled" + GRAY + "."), true);
					}
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				Main.INSTANCE.commandManager.sendClientChatMessage("the module " + RED + moduleName + GRAY + " was not found.", true);
			}
		}else Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}
