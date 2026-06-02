package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;

public class SaveConfig extends Command {

    public SaveConfig() {
        super("saveConfig", "save the current client config to file.", "saveConfig", "save");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }
        Main.INSTANCE.save.save();
        Main.INSTANCE.commandManager.sendClientChatMessage("saved config.", true);
    }
}
