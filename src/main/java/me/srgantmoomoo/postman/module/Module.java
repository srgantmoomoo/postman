package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.KeybindSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class Module {
    private final String name;
    private final String description;
    private final Category category;
    private KeybindSetting key = new KeybindSetting(0);
    private boolean enabled;
    private List<Setting> settings = new ArrayList<>();

    public Module(String name, String description, Category category, int key) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.key.setKey(key);
        addSettings(this.key);
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
        this.settings.sort(Comparator.comparingInt(s -> s == key ? 1 : 0));
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public Setting getSettingByName(String name) {
        for(Setting setting : this.getSettings()) {
            if(setting.getName().equals(name)) {
                return setting;
            }
        }
        return null;
    }

    public KeybindSetting getKeybindSetting() {
        return key;
    }

    public int getKey() {
        return key.getKey();
    }

    public void setKey(int key) {
        this.key.setKey(key);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isModuleEnabled() {
        return enabled;
    }

    private void setEnabled(boolean enabled) {
        this.enabled = enabled;

        Main.INSTANCE.save();
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onEvent(Event e) {}

    public void enable() {
        onEnable();
        setEnabled(true);
    }

    public void disable() {
        onDisable();
        setEnabled(false);
    }

    public void toggle() {
        if(enabled)
            disable();
        else
            enable();
    }
}
