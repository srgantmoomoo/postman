package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

public class GetChunkPos extends Command {
    public GetChunkPos() {
        super("getChunkPos", "tells you your current chunk pos.", "getChunkPos", "gcp");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        Main.INSTANCE.commandManager.sendClientChatMessage("chunk pos: " + Formatting.WHITE + MinecraftClient.getInstance().player.getChunkPos(), true);
    }
}
