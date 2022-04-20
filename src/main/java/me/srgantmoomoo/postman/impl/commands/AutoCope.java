package me.srgantmoomoo.postman.impl.commands;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;

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
			
			me.srgantmoomoo.postman.impl.modules.pvp.AutoCope.setMessage(args[0] + " " + msg.toString());
			Main.INSTANCE.commandManager.sendClientChatMessage("set autoCope message to " + WHITE + args[0] + " " + msg.toString() + GRAY + ".", true);
		}else
			Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
	}

}
