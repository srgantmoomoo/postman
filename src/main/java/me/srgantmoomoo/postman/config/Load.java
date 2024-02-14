package me.srgantmoomoo.postman.config;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.*;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.Iterator;

public class Load {
    public File MainDirectory;

    public Load() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "postman");
        if(!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }
        load();
    }

    public void load() {
        loadModules();
        loadSettings();
        loadPrefix();
    }

    public void loadModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
            DataInputStream ds = new DataInputStream(fs); // like the nintendo ds cool :o.
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));

            String line;
            while ((line = br.readLine()) != null) {
                Iterator var6 = Main.INSTANCE.moduleManager.getModules().iterator();

                while (var6.hasNext()) {
                    Module m = (Module) var6.next();
                    if (m.getName().equals(line)) {
                        m.toggle();
                    }
                }
            }
            br.close();
        }catch (Exception e) {}
    }

    public void loadSettings() {
        try {
            File file = new File(MainDirectory, "settings.txt");
            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));

            String line;

            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String moduleName = curLine.split(":")[0];
                String settingName = curLine.split(":")[1];
                String value = curLine.split(":")[2];

                Module module = Main.INSTANCE.moduleManager.getModule(moduleName);
                if(module != null) {
                    if (!settingName.equalsIgnoreCase("keybind")) {
                        Setting setting = Main.INSTANCE.settingManager.getSetting(module, settingName);
                        if (setting instanceof BooleanSetting) {
                            ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(value));
                        }

                        if (setting instanceof NumberSetting) {
                            ((NumberSetting) setting).setValue(Double.parseDouble(value));
                        }

                        if (setting instanceof ModeSetting && ((ModeSetting) setting).getModes().toString().contains(value)) { // u have to make sure the mode getting loaded actually still exists or else u will have angry mob of ppl telling u ur config is fucking garbage... but actually yes ur config is fucking garbage because u wrote it when u were fucking monke and didn't know wtf u were doing, like seriously come on now, who the fuck writes a config in a normal fucking txt file, r u fucking stupid??????? like just do it in fucking json u fucking dumb cunt. goated redpilled postman comment.
                            ((ModeSetting) setting).setMode(value);
                        }

                        if(setting instanceof ColorSetting) {
                            ((ColorSetting) setting).setRainbow(Boolean.parseBoolean(curLine.split(":")[3]));
                            ((ColorSetting) setting).fromInteger(Integer.parseInt(value));
                        }

                        if (setting instanceof KeybindSetting) {
                            ((KeybindSetting) setting).setKey(Integer.parseInt(value));
                        }
                    }else
                        module.setKey(Integer.parseInt(value));
                }
            }

            br.close();
        } catch (Exception e) {
        }
    }

    public void loadPrefix() {
        try {
            File file = new File(MainDirectory, "prefix.txt");
            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));

            String line;
            while ((line = br.readLine()) != null) {
                Main.INSTANCE.commandManager.setPrefix(line);
            }

            br.close();
        } catch (Exception e) {
        }
    }
}
