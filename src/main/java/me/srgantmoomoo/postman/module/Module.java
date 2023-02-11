package me.srgantmoomoo.postman.module;

import com.lukflug.panelstudio.base.IBoolean;
import com.lukflug.panelstudio.base.IToggleable;
import com.lukflug.panelstudio.setting.IModule;
import com.lukflug.panelstudio.setting.ISetting;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.KeybindSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Module implements IModule {

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

    public List<Setting> getModuleSettings() {
        return settings;
    }

    @Override
    public Stream<ISetting<?>> getSettings() {
        return settings.stream().filter(setting->setting instanceof ISetting).sorted((a,b)->a.getName().compareTo(b.getName())).map(setting->(ISetting<?>)setting);
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

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isModuleEnabled() {
        return enabled;
    }

    @Override
    public IToggleable isEnabled() {
        return new IToggleable() {
            @Override
            public boolean isOn() {
                return enabled;
            }

            @Override
            public void toggle() {
                enabled=!enabled;
            }
        };
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

    public void onEvent(Event e) {}

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

    @Override
    public IBoolean isVisible() {
        return ()->true;
    }

}
