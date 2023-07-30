package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.util.Formatting;

import java.text.Format;

public class Toggle extends Command {

    public Toggle() {
        super("toggle", "toggle modules on or off.", "toggle <module>", "t");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        String userInput = args[0];
        boolean found = false;
        for(Module module : Main.INSTANCE.moduleManager.getModules()) {
            if(userInput.equalsIgnoreCase(module.getName())) {
                module.toggle();
                Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.WHITE + module.getName() + Formatting.GRAY + " is now " + (module.isModuleEnabled() ? Formatting.GREEN + "enabled" :
                        Formatting.RED + "disabled") + Formatting.GRAY + ".", true);
                found = true;
                break;
            }
        }

        if(!found) Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.WHITE + userInput + Formatting.GRAY + " could not be found.", true);
    }
}
