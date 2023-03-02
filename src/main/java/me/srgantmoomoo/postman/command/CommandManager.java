package me.srgantmoomoo.postman.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    public ArrayList<Command> commands = new ArrayList<Command>();
    private String prefix = ",";

    public CommandManager() {

    }

    public String getPrefix() {
        return prefix;
    }

    // called in MixinClientConnection.
    public void onClientChat(String input) {
        if(!input.startsWith(prefix))
            return;

        if(input.split(" ").length > 0) {
            boolean commandFound = false;
            String commandName = input.split(" ")[0];

            if(commandName.equals("") || commandName.equals("help")) {
                // do help stuff
            }else {
                for(Command c : commands) {
                    if(c.getAliases().contains(commandName) || c.getName().equalsIgnoreCase(commandName)) {
                        c.onCommand(Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length), input);
                        commandFound = true;
                        break;
                    }
                }
                if(!commandFound)
                    // help msggs.
                    return;
            }
        }
    }

    // opens chat when prefix is pressed, called in MixinKeyboard.
    public void onKeyPress() {
        if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), prefix.charAt(0))) {
            if(prefix.length() == 1) {
                MinecraftClient.getInstance().setScreen(new ChatScreen(""));
            }
        }

    }
}
