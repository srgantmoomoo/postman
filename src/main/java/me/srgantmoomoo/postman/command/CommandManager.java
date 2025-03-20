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
    private ArrayList<Command> commands = new ArrayList<Command>();
    private String prefix = ",";

    public CommandManager() {
        // organized in a way to logically display in help command
        commands.add(new Help());
        commands.add(new Prefix());
        commands.add(new Bind());
        commands.add(new ListModules());
        commands.add(new ListSettings());
        commands.add(new Toggle());
        commands.add(new Setting());
        commands.add(new Clear());
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;

        if(Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.savePrefix();
            } catch (Exception ignored) {}
        }
    }

    // called in MixinClientConnection.
    public void onClientChat(String input) {
        if(!input.startsWith(prefix))
            return;

        input = input.substring(prefix.length());
        if(input.split(" ").length > 0) {
            boolean commandFound = false;
            String commandName = input.split(" ")[0];
            for(Command c : commands) {
                if(c.getName().equalsIgnoreCase(commandName) || c.getAliases().contains(commandName)) {
                    c.onCommand(Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length), input);
                    commandFound = true;
                    break;
                }
            }
            if(!commandFound)
                sendClientChatMessage(Formatting.RED + "command does not exist, use " + Formatting.ITALIC +
                        Formatting.WHITE + prefix + "help " + Formatting.RESET + Formatting.RED + "for help.", true);
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

    public void sendClientChatMessage(String message, boolean prefix) {
        String messagePrefix = Formatting.AQUA + "" + Formatting.ITALIC + "@" + Main.INSTANCE.NAME + ": " +
                Formatting.RESET;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal((prefix ? messagePrefix +
                Formatting.GRAY + message : Formatting.GRAY + message)));
    }

    public void sendCorrectionMessage(Command command) {
        String commWithDividers = command.getSyntax().replace("|", Formatting.WHITE + "" + Formatting. ITALIC + "|" +
                Formatting.GRAY + "" + Formatting.ITALIC); // turns dividers grey for look better :)
        sendClientChatMessage(Formatting.RED + "correct usage of " + Formatting.WHITE + command.getName() +
                Formatting.RED + " command -> \n" +
                Formatting.WHITE + "[" + Formatting.GRAY + Formatting.ITALIC + prefix + commWithDividers +
                Formatting.WHITE + "]", true);
    }
}
