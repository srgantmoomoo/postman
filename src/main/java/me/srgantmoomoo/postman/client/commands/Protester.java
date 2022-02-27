package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class Protester extends Command {
	
    public Protester() {
		super("protester", "edit the protester msg.", "protester <msg>", "pr");
	}
    
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length >= 1) {
			StringBuilder msg = new StringBuilder();
            boolean flag = true;
            for (String string : args) {
                if (flag) {
                    flag = false;
                    continue;
                }
                msg.append(string).append(" ");
            }
			
			me.srgantmoomoo.postman.client.modules.player.Protester.setMessage(args[0] + " " + msg.toString());
			Main.INSTANCE.moduleManager.addChatMessage("set protester message to " + ChatFormatting.GREEN + args[0] + " " + msg.toString() + ChatFormatting.GRAY + ".");
		}else Main.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
	}
}
