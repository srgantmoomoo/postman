package me.srgantmoomoo.postman.command;

import me.srgantmoomoo.postman.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    public ArrayList<Command> commands = new ArrayList<Command>();
    private String prefix = ",";

    public CommandManager() {

    }

    // called in MixinClientConnection.
    public void onClientChat(String input) {
        if(!input.startsWith(prefix))
            return;

        if(input.split(" ").length > 0) {
            boolean commandFound = false;
            String commandName = input.split(" ")[0];
            if(commandName.equals("") || commandName.equals("help")) {
                sendClientChatMessage("\n" + Formatting.GRAY + Formatting.BOLD + "i love postman <3" + "\n" + Formatting.RESET, false);
                for(Command c : commands) {
                    sendClientChatMessage(c.getName() + Formatting.WHITE + " - " + Formatting.AQUA + Formatting.ITALIC + " [" + c.getSyntax() + "]" + Formatting.RESET + Formatting.GRAY + ".", false);
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
    }

    public void sendClientChatMessage(String message, boolean prefix) {
        String messagePrefix = Formatting.WHITE + "" + Formatting.ITALIC + "@" + Main.INSTANCE.NAME + ": " + Formatting.RESET;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal(Formatting.GRAY + (prefix ? messagePrefix + message : "")));
    }

    public void sendCorrectionMessage(String name, String syntax) {
        sendClientChatMessage("correct usage of " + Formatting.WHITE + name + Formatting.GRAY + " command -> " + Formatting.WHITE + prefix + syntax + Formatting.GRAY + ".", true);
    }
}