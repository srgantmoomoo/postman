package me.srgantmoomoo.postman.config;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.*;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save {
    public File MainDirectory;

    public Save() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "postman");
        if(!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }
    }

    public void save() {
        saveModules();
        saveSettings();
        savePrefix();
    }

    private void writeFile(ArrayList<String> toSave, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for(String string : toSave) {
                printWriter.println(string);
            }
            printWriter.close();
        }catch (FileNotFoundException ignored) {}
    }

    public void saveModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
            ArrayList<String> toSave = new ArrayList<>();
            for(Module module : Main.INSTANCE.moduleManager.getModules()) {
                if(module.isModuleEnabled() && !module.getName().equalsIgnoreCase("clickGui")) {
                    toSave.add(module.getName());
                }
            }
            writeFile(toSave, file);
        }catch (Exception ignored) {}
    }

    public void saveSettings() {
        try {
            File file = new File(MainDirectory, "settings.txt");
            ArrayList<String> toSave = new ArrayList<>();
            for(Module module : Main.INSTANCE.moduleManager.getModules()) {
                for(Setting setting : module.getModuleSettings()) {
                    if(setting instanceof BooleanSetting boo) {
                        toSave.add(module.getName() + ":" + setting.getName() + ":" + boo.isEnabled());
                    }
                    if(setting instanceof NumberSetting numb) {
                        toSave.add(module.getName() + ":" + setting.getName() + ":" + numb.getValue());
                    }
                    if(setting instanceof ModeSetting mode) {
                        toSave.add(module.getName() + ":" + setting.getName() + ":" + mode.getMode());
                    }
                    if(setting instanceof ColorSetting color) {
                        toSave.add(module.getName() + ":" + setting.getName() + ":" + color.toInteger() + ":" + color.getRainbow());
                    }
                    if(setting instanceof KeybindSetting keybind) {
                        toSave.add(module.getName() + ":" + setting.getName() + ":" + module.getKey());
                    }
                }
            }
            writeFile(toSave, file);
        }catch (Exception ignored) {}
    }

    public void savePrefix() {
        try {
            File file = new File(MainDirectory, "prefix.txt");
            ArrayList<String> toSave = new ArrayList<>();
            toSave.add(Main.INSTANCE.commandManager.getPrefix());
            writeFile(toSave, file);
        }catch (Exception ignored) {}
    }
}
