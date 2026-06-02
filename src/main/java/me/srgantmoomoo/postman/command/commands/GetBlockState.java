package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

public class GetBlockState extends Command {
    public GetBlockState() {
        super("getBlockState", "tells you your current blocks state.", "getBlockState", "gbs");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        Main.INSTANCE.commandManager.sendClientChatMessage("block state: " + Formatting.WHITE + MinecraftClient.getInstance().player.getBlockStateAtPos(), true);
    }
}
