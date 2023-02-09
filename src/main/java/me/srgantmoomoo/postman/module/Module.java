package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.module.setting.Setting;

import java.util.ArrayList;
import java.util.List;

public class Module {

    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private List<Setting> settings = new ArrayList<>();

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
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
