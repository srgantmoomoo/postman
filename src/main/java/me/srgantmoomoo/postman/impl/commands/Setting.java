package me.srgantmoomoo.postman.impl.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.command.Command;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;

public class Setting extends Command {

    public Setting() {
        super("setting", "adjust module settings without a cap on their value.", "setting <module> <setting> <value>", "s");
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
            Main.INSTANCE.commandManager.sendClientChatMessage("the module " + RED + moduleInput + GRAY + " was not found.", true);
            return;
        }

        if(setting == null) {
            Main.INSTANCE.commandManager.sendClientChatMessage("the setting " + RED + settingNameInput + GRAY + " was not found for the module " + WHITE + moduleInput + GRAY + ".", true);
            return;
        }

        if(setting instanceof BooleanSetting) {
            if(settingValueInput.equalsIgnoreCase("true") || settingValueInput.equalsIgnoreCase("false")) {
                ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(settingValueInput));
                Main.INSTANCE.commandManager.sendClientChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name +  GRAY + " was set to " + (settingValueInput.equalsIgnoreCase("true") ? GREEN + settingValueInput + GRAY + "." : RED + settingValueInput + GRAY + "."), true);
            }else
                Main.INSTANCE.commandManager.sendClientChatMessage("boolean value must be either " + GREEN + "true" + GRAY + " or " + RED + "false" + GRAY + ".", true);
        }
    }

}
