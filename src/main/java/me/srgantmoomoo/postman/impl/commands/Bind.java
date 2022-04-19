package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.module.Module;

public class Bind extends Command {
	public Bind() {
		super("bind", "bind modules to specific keys.", "bind <name> <key> | bind clear", "bi");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String moduleName = args[0], keyName = args[1];
			boolean moduleFound = false;
			
			for(Module module : Main.INSTANCE.moduleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.keyCode.setKey(Keyboard.getKeyIndex(keyName.toUpperCase()));

					Main.INSTANCE.commandManager.sendClientChatMessage(module.getName() + " bound to " + ChatFormatting.GREEN + Keyboard.getKeyName(module.getKey()), true);;
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				Main.INSTANCE.commandManager.sendClientChatMessage(ChatFormatting.DARK_RED + "module not found.", true);
			}
		}else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("clear")) {
				for(Module module : Main.INSTANCE.moduleManager.modules) {
					module.keyCode.setKey(Keyboard.KEY_NONE);
				}
				Main.INSTANCE.commandManager.sendClientChatMessage(ChatFormatting.GREEN + "cleared all binds" + ChatFormatting.WHITE + ".", true);
			} else
				Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}
