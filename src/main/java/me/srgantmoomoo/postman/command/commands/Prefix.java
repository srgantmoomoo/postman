package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import net.minecraft.util.Formatting;

public class Prefix extends Command {

    public Prefix() {
        super("prefix", "set the command prefix of the client.", "prefix <prefix>", "p");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        Main.INSTANCE.commandManager.setPrefix(args[0]);
        Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.WHITE + "prefix " + Formatting.GRAY + "is now set to " + Formatting.GREEN + args[0], true);

        if(Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.savePrefix();
            } catch (Exception ignored) {}
        }
    }

}
