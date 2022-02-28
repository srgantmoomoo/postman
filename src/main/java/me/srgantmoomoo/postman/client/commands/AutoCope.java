package me.srgantmoomoo.postman.client.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;

public class AutoCope extends Command {
	
    public AutoCope() {
		super("autoCope", "edit the autoCope msg.", "autoCope <msg>", "ac");
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
			
			me.srgantmoomoo.postman.client.modules.pvp.AutoCope.setMessage(args[0] + " " + msg.toString());
			Main.INSTANCE.commandManager.sendClientChatMessage("set autoCope message to " + ChatFormatting.GREEN + args[0] + " " + msg.toString() + ChatFormatting.GRAY + ".", true);
		}else Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}
}
