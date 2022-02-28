package me.srgantmoomoo.postman.client.commands;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class Bind extends Command {
	
	public Bind() {
		super("bind", "bind modules to specific keys.", "bind <name> <key> | bind clear", "bi");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
			boolean moduleFound = false;
			
			for(Module module : Main.INSTANCE.moduleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.keyCode.setKeyCode(Keyboard.getKeyIndex(keyName.toUpperCase()));

					Main.INSTANCE.commandManager.sendClientChatMessage(String.format(ChatFormatting.GREEN + "%s " + ChatFormatting.GRAY + "was bound to" + ChatFormatting.GREEN + " %s", module.name, Keyboard.getKeyName(module.getKey())), true);;
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				Main.INSTANCE.commandManager.sendClientChatMessage(ChatFormatting.DARK_RED + "module not found.", true);
			}
		}
		
		if(args.length == 1) {
			String clear = args[0];
			if(clear.equalsIgnoreCase("clear")) {
				for(Module module : Main.INSTANCE.moduleManager.modules) {
					module.keyCode.setKeyCode(Keyboard.KEY_NONE);
				}
				Main.INSTANCE.commandManager.sendClientChatMessage("cleared all binds.", true);
			} else Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}
		if(args.length == 0) Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}
