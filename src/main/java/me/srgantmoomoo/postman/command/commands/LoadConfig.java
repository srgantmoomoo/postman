package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;

public class LoadConfig extends Command {

    public LoadConfig() {
        super("loadConfig", "load the current config from file to client.", "loadConfig", "load");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }
        Main.INSTANCE.load.load();
        Main.INSTANCE.commandManager.sendClientChatMessage("loaded config.", true);
    }
}
