package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Formatting;

public class Bind extends Command {

    public Bind() {
        super("bind", "bind a module to a specific key.", "bind <module> <key> | bind <module> none | bind clear", "b");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length == 0 || args.length > 2) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        String userInput = args[0];
        switch (args.length) {
            case 2 -> {
                String keyInput = args[1];
                boolean found = false;
                for (Module module : Main.INSTANCE.moduleManager.getModules()) {
                    if (module.getName().equalsIgnoreCase(userInput)) {
                        found = true;
                        if (keyInput.equalsIgnoreCase("none")) {
                            module.setKey(0);
                            Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.WHITE + module.getName() + Formatting.GRAY + " is now " + Formatting.RED + "unbound"
                                    + Formatting.GRAY + ".", true);
                        } else {
                            try {
                                int keyCode = InputUtil.fromTranslationKey("key.keyboard." + keyInput).getCode();
                                module.setKey(keyCode);
                                Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.WHITE + module.getName() + Formatting.GRAY + " is now bound to " + Formatting.GREEN
                                        + keyInput + Formatting.GRAY + ".", true);
                            } catch (IllegalArgumentException ignored) {
                                Main.INSTANCE.commandManager.sendClientChatMessage("the key " + Formatting.WHITE + keyInput + Formatting.GRAY + " is " + Formatting.RED + "invalid"
                                        + Formatting.GRAY+ " and cannot be bound.", true);
                            }
                        }
                    }
                }
                if(!found)
                    Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.RED + userInput + Formatting.GRAY + " could not be found.", true);
            }
            case 1 -> {
                if (!userInput.equalsIgnoreCase("clear")) {
                    Main.INSTANCE.commandManager.sendCorrectionMessage(this);
                    return;
                }
                for(Module module : Main.INSTANCE.moduleManager.getModules()) {
                    module.setKey(0);
                }
                Main.INSTANCE.commandManager.sendClientChatMessage("all binds are now " + Formatting.RED + "cleared" + Formatting.GRAY + ".", true);
            }
        }
    }
}
