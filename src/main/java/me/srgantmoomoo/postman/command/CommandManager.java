package me.srgantmoomoo.postman.command;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.commands.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    public ArrayList<Command> commands = new ArrayList<Command>();
    private String prefix = ",";

    public CommandManager() {
        commands.add(new Example());
        commands.add(new Prefix());
    }

    // called in MixinClientConnection.
    public void onClientChat(String input) {
        if(!input.startsWith(prefix))
            return;

        input = input.substring(prefix.length());
        if(input.split(" ").length > 0) {
            //TODO fix this fucking shit.
            boolean commandFound = false;
            String commandName = input.split(" ")[0];
            if(commandName.equals("") || commandName.equals("help")) {
                sendClientChatMessage("\n" + Formatting.GRAY + Formatting.BOLD + "i love postman <3" + "\n" + Formatting.RESET, false);
                for(Command c : commands) {
                    sendClientChatMessage(c.getName() + Formatting.WHITE + " - " + c.getDescription() + Formatting.AQUA + Formatting.ITALIC + " [" + c.getSyntax() + "]" + Formatting.RESET + Formatting.GRAY + ".", false);
                }
                sendClientChatMessage("\n" + Formatting.RESET + Formatting.GRAY + Formatting.BOLD + "i hate postman." + "\n", false);
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;

        if(Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.saveSettings();
            } catch (Exception e) {}
        }
    }

    public void sendClientChatMessage(String message, boolean prefix) {
        String messagePrefix = Formatting.GRAY + "" + Formatting.ITALIC + "@" + Main.INSTANCE.NAME + ": " + Formatting.RESET;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal((prefix ? messagePrefix + Formatting.GRAY + message : Formatting.GRAY + message))); // Text.literal(Formatting.GRAY + (prefix ? messagePrefix + message : "")
    }

    public void sendCorrectionMessage(Command command) {
        sendClientChatMessage("correct usage of " + Formatting.WHITE + command.getName() + Formatting.GRAY + " command -> " + Formatting.WHITE + prefix + command.getSyntax() + Formatting.GRAY + ".", true);
    }
}
