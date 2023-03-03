package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;

public class Example extends Command {

    public Example() {
        super("example", "asfdgkhjasf.", "example", "ex");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
            return;
        }

        Main.INSTANCE.commandManager.sendClientChatMessage("helllooooo worrrlllddd!!!", true);
    }

}
