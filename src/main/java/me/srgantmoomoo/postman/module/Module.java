package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.KeybindSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Module {

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
        addSettings(this.key);
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
        this.settings.sort(Comparator.comparingInt(s -> s == key ? 1 : 0));
    }

    public List<Setting> getSettings() {
        return settings;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled)
            // subscribe
            return;
        else
            // un subscribe
            return;
    }

    public void toggle() {
        if(enabled)
            disable();
        else
            enable();
    }

    public void onEnable() {}

    public void onDisable() {}

    public void enable() {
        onEnable();
        setEnabled(true);
        // subscribe
    }

    public void disable() {
        onDisable();
        setEnabled(false);
        //un subscribe
    }

}
