package me.srgantmoomoo.postman.command.commands;


import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.modules.player.Protester;
import net.minecraft.util.Formatting;

public class ProtesterMsg extends Command {
    public ProtesterMsg() {
        super("protesterMsg", "edit the customMsg for the protester module.", "protesterMsg <msg>", "pm");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length < 1) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }
        StringBuilder msg = new StringBuilder();
        boolean flag = true;
        for (String string : args) {
            if (flag) {
                flag = false;
                continue;
            }
            msg.append(string).append(" ");
        }
        Protester.customMsgArg = args[0] + " " + msg.toString();
        Main.INSTANCE.commandManager.sendClientChatMessage("set protester message to " + Formatting.WHITE + args[0] + " " + msg.toString() + Formatting.GRAY + ".", true);

    }
}