package me.srgantmoomoo.postman.module.setting;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;

import java.util.ArrayList;

public class SettingManager {
    private ArrayList<Setting> settings;

    public SettingManager() {
        this.settings = new ArrayList<Setting>();
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public Setting getSetting(Module module, String name) {
        for(Module m : Main.INSTANCE.moduleManager.getModules()) {
            for(Setting s : m.getSettings()) {
                if(s.getName().equalsIgnoreCase(name) && s.getParent() == module)
                    return s;
            }
        }
        System.err.println("setting not found: " + name);
        return null;
    }

    public ArrayList<Setting> getSettingsInModule(Module module) {
        ArrayList<Setting> result = new ArrayList<Setting>();
        for(Setting s : getSettings()) {
            if(s.getParent().equals(module))
                result.add(s);
        }
        if(result.isEmpty())
            return null;
        return result;
    }
}
