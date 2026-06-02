package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import net.minecraft.client.MinecraftClient;

public class ClearChat extends Command {

    public ClearChat() {
        super("clearChat", "clears the chat.", "clearChat", "c");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        MinecraftClient.getInstance().inGameHud.getChatHud().clear(true);
    }
}
