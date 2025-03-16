package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import net.minecraft.util.Formatting;

public class Help extends Command {
    public Help() {
        super("help", "helpful instructions for postman.", "help", "h");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.GRAY + "" + Formatting.BOLD + Main.INSTANCE.NAME +
                " " + Main.INSTANCE.VERSION + "!", false); // display client name & version
        Main.INSTANCE.commandManager.sendClientChatMessage("", false); // space
        Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.AQUA + "@" + Formatting.ITALIC +
                Main.INSTANCE.NAME, false); // display @client-name

        // display each command
        for(Command c : Main.INSTANCE.commandManager.getCommands()) {
            Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.BLUE + c.getName() + Formatting.GRAY +
                    " - " + c.getDescription(), false); // display command name & description
            Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.WHITE + " [" +
                    Main.INSTANCE.commandManager.getPrefix() + c.getSyntax() + "]", false);
        }

        Main.INSTANCE.commandManager.sendClientChatMessage("", false); // space
        Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.GRAY + "" + Formatting.BOLD + "i love postman" +
                Formatting.AQUA + " <3", false); // display end of help page message
    }
}
