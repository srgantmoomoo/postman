package me.srgantmoomoo.postman.impl.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;

public class Setting extends Command {

    public Setting() {
        super("setting", "adjust module settings.", "setting <module> <setting> <value>", "s");
    }

    ChatFormatting RED = ChatFormatting.RED;
    ChatFormatting GRAY = ChatFormatting.GRAY;
    ChatFormatting WHITE = ChatFormatting.WHITE;
    ChatFormatting GREEN = ChatFormatting.GREEN;

    @Override
    public void onCommand(String[] args, String commnand) {
        if(args.length != 3) {
            Main.INSTANCE.commandManager.sendCorrectionMessage(getName(), getSyntax());
            return;
        }

        String moduleInput = args[0];
        String settingNameInput = args[1];
        String settingValueInput = args[2];
        Module module = Main.INSTANCE.moduleManager.getModuleByName(moduleInput);
        me.srgantmoomoo.postman.framework.module.setting.Setting setting = Main.INSTANCE.settingManager.getSettingByName(module, settingNameInput);

        if(module == null) {
            Main.INSTANCE.commandManager.sendClientChatMessage("the module " + WHITE + moduleInput + GRAY + " was not found.", true);
            return;
        }

        if(setting == null) {
            Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + WHITE + settingNameInput + GRAY + " was not found for the module " + WHITE + moduleInput + GRAY + ".", true);
            return;
        }

        if(setting instanceof BooleanSetting) {
            if(settingValueInput.equalsIgnoreCase("true") || settingValueInput.equalsIgnoreCase("false")) {
                ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(settingValueInput));
                Main.INSTANCE.commandManager.sendClientChatMessage("setting " + WHITE + setting.name + GRAY + " of " + WHITE + module.name +  GRAY + " was set to " + (settingValueInput.equalsIgnoreCase("true") ? GREEN + settingValueInput + GRAY + "." : RED + settingValueInput + GRAY + "."), true);
            }else
                Main.INSTANCE.commandManager.sendClientChatMessage("boolean value must be either " + GREEN + "true" + GRAY + " or " + RED + "false" + GRAY + ".", true);
        }

        if(setting instanceof NumberSetting) {
            try {
                double val = Double.parseDouble(settingValueInput);

                if(val > ((NumberSetting) setting).getMaximum()) val = ((NumberSetting) setting).getMaximum();
                else if(val < ((NumberSetting) setting).getMinimun()) val = ((NumberSetting) setting).getMinimun();

                ((NumberSetting) setting).setValue(val);
                Main.INSTANCE.commandManager.sendClientChatMessage("setting " + WHITE + setting.name + GRAY + " of " + WHITE + module.name +  GRAY + " was set to " + WHITE + val + GRAY + ".", true);
            } catch (NumberFormatException invalid) {
                Main.INSTANCE.commandManager.sendClientChatMessage("number value " + WHITE + settingValueInput + GRAY + " is " + RED + "invalid" + GRAY + ".", true);
            }
        }

        if(setting instanceof ModeSetting) {
            if(((ModeSetting) setting).modes.contains(settingValueInput)) {
                ((ModeSetting) setting).setMode(settingValueInput);
                Main.INSTANCE.commandManager.sendClientChatMessage("setting " + WHITE + setting.name + GRAY + " of " + WHITE + module.name +  GRAY + " was set to " + WHITE + settingValueInput + GRAY + ".", true);
            }else
                Main.INSTANCE.commandManager.sendClientChatMessage("the mode " + WHITE + settingValueInput + GRAY + " does not exist for the setting " + WHITE + setting.name + " in the module " + WHITE + module.name + GRAY + ".", true);
        }

        if(setting instanceof ColorSetting) {
            try {
                int valR = Integer.parseInt(settingValueInput.substring(0, 3));
                int valG = Integer.parseInt(settingValueInput.substring(3, 6));
                int valB = Integer.parseInt(settingValueInput.substring(6, 9));
                int valA = Integer.parseInt(settingValueInput.substring(9, 12));

                ((ColorSetting) setting).setValue(false, new JColor(valR, valG, valB, valA));
                Main.INSTANCE.commandManager.sendClientChatMessage("setting " + WHITE + setting.name + GRAY + " of " + WHITE + module.name +  GRAY + " was set to whatever the hell color " + WHITE + settingValueInput + GRAY + " is.", true);
            } catch (Exception invalid) {
                Main.INSTANCE.commandManager.sendClientChatMessage("color value " + WHITE + settingValueInput + GRAY + " is invalid, colors should be input in " + WHITE + "RRRGGGBBBAAA" + GRAY + " format.", true);
            }
        }

    }

}
