package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.util.Formatting;

public class ListSettings extends Command {

    public ListSettings() {
        super("listSettings", "list the settings of a specific module.", "listsettings <module>", "ls");
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
            if(module.getName().equalsIgnoreCase(userInput)) {
                found = true;
                Main.INSTANCE.commandManager.sendClientChatMessage(Formatting.BOLD + module.getName(), false);
                for(Setting setting : module.getModuleSettings()) {
                    if(setting instanceof BooleanSetting)
                        Main.INSTANCE.commandManager.sendClientChatMessage(setting.getName() + (((BooleanSetting) setting).isEnabled() ?
                                Formatting.GREEN + " enabled" : Formatting.RED + " disabled") + Formatting.GRAY + ".", false);
                    if(setting instanceof NumberSetting)
                        Main.INSTANCE.commandManager.sendClientChatMessage(setting.getName() + Formatting.WHITE + " " + ((NumberSetting) setting).getValue() + Formatting.GRAY + ".", false);
                    if(setting instanceof ModeSetting)
                        Main.INSTANCE.commandManager.sendClientChatMessage(setting.getName() + Formatting.GREEN + " " + ((ModeSetting) setting).getMode() + Formatting.WHITE
                                + " (" + Formatting.GRAY + ((ModeSetting) setting).getModes() + Formatting.WHITE + ")" + Formatting.GRAY + ".", false);
                    if(setting instanceof ColorSetting)
                        Main.INSTANCE.commandManager.sendClientChatMessage(setting.getName() + Formatting.WHITE + " " + ((ColorSetting) setting).getValue().getRed() + " "
                                + ((ColorSetting) setting).getValue().getGreen() + " " + ((ColorSetting) setting).getValue().getBlue() + " " + ((ColorSetting) setting).getValue().getAlpha()
                                + Formatting.GRAY + ".", false);
                }
            }
        }
        if(!found)
            Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.RED + userInput + Formatting.GRAY + " could not be found.", true);
    }
}
