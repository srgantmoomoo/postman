package me.srgantmoomoo.postman.command.commands;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.command.Command;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.util.Formatting;

import java.awt.*;

public class Setting extends Command {

    public Setting() {
        super("setting", "change a setting of a module.", "setting <module> <setting> <value>", "set");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 3) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(this);
            return;
        }

        String moduleInput = args[0];
        String settingInput = args[1];
        String valueInput = args[2];
        boolean moduleFound = false;
        boolean settingFound = false;
        for(Module module : Main.INSTANCE.moduleManager.getModules()) {
            if(module.getName().equalsIgnoreCase(moduleInput)) {
                moduleFound = true;
                for(me.srgantmoomoo.postman.module.setting.Setting setting : module.getModuleSettings()) {
                    if(setting.getName().equalsIgnoreCase(settingInput)) {
                        settingFound = true;
                        if(setting instanceof BooleanSetting) {
                            if(valueInput.equalsIgnoreCase("true") || valueInput.equalsIgnoreCase("false")) {
                                ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(valueInput));
                                Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + Formatting.WHITE + setting.getName() + Formatting.GRAY + " of " + Formatting.WHITE
                                        + module.getName() + Formatting.GRAY + " is now set to " + (((BooleanSetting) setting).isEnabled() ? Formatting.GREEN + "true" : Formatting.RED + "false")
                                        + Formatting.GRAY + ".", true);
                            }else
                                Main.INSTANCE.commandManager.sendClientChatMessage("your input for the boolean value must be either " + Formatting.GREEN + "true" + Formatting.GRAY
                                        + " or " + Formatting.RED + "false" + Formatting.GRAY + ".", true);
                        }
                        if(setting instanceof NumberSetting) {
                            try {
                                double value = Double.parseDouble(valueInput);
                                if(value > ((NumberSetting) setting).getMaximum()) value = ((NumberSetting) setting).getMaximum();
                                else if(value < ((NumberSetting) setting).getMinimum()) value = ((NumberSetting) setting).getMinimum();

                                ((NumberSetting) setting).setValue(value);
                                Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + Formatting.WHITE + setting.getName() + Formatting.GRAY + " of " + Formatting.WHITE
                                        + module.getName() + Formatting.GRAY + " is now set to " + Formatting.GREEN + ((NumberSetting) setting).getNumber() + Formatting.GRAY + ".", true);
                            }catch (NumberFormatException invalid) {
                                Main.INSTANCE.commandManager.sendClientChatMessage("your input " + Formatting.RED + valueInput + Formatting.GRAY + " is not a valid number.", true);
                            }
                        }
                        if(setting instanceof ModeSetting) {
                            if(((ModeSetting) setting).getModes().contains(valueInput)) {
                                ((ModeSetting) setting).setMode(valueInput);
                                Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + Formatting.WHITE + setting.getName() + Formatting.GRAY + " of " + Formatting.WHITE
                                        + module.getName() + Formatting.GRAY + " is now set to " + Formatting.GREEN + ((ModeSetting) setting).getMode() + Formatting.GRAY + ".", true);
                            }else
                                Main.INSTANCE.commandManager.sendClientChatMessage("the mode " + Formatting.RED + valueInput + Formatting.GRAY + " could not be found for " + Formatting.WHITE
                                        + setting.getName() + Formatting.GRAY +".", true);
                        }
                        if(setting instanceof ColorSetting) {
                            try {
                                int R = Integer.parseInt(valueInput.substring(0, 3));
                                int G = Integer.parseInt(valueInput.substring(3, 6));
                                int B = Integer.parseInt(valueInput.substring(6, 9));
                                int A = Integer.parseInt(valueInput.substring(9, 12));

                                // gross
                                if(R > 255) R = 255;
                                if(G > 255) G = 255;
                                if(B > 255) B = 255;
                                if(A > 255) A = 255;

                                ((ColorSetting) setting).setValue(new Color(R, G, B, A));
                                Main.INSTANCE.commandManager.sendClientChatMessage("the color setting " + Formatting.WHITE + setting.getName() + Formatting.GRAY + " of " + Formatting.WHITE
                                        + module.getName() + Formatting.GRAY + " is now set to " + Formatting.GREEN + R + " " + G + " " + B + " " + A + Formatting.GRAY + ".", true);
                            }catch (Exception invalid) {
                                Main.INSTANCE.commandManager.sendClientChatMessage("the color " + Formatting.RED + valueInput + Formatting.GRAY + " is invalid. colors should be input in the format "
                                        + Formatting.WHITE + "RRRGGGBBBAAA" + Formatting.GRAY + ".", true);
                            }
                        }
                    }
                }
            }
        }
        if(!moduleFound)
            Main.INSTANCE.commandManager.sendClientChatMessage("the module " + Formatting.RED + moduleInput + Formatting.GRAY + " could not be found.", true);
        else if(!settingFound)
            Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + Formatting.RED + settingInput + Formatting.GRAY + " could not be found for the module " + Formatting.WHITE
                    + moduleInput + Formatting.GRAY + ".", true);
    }
}
