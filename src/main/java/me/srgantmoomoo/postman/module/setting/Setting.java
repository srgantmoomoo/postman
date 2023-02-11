package me.srgantmoomoo.postman.module.setting;

import com.lukflug.panelstudio.base.IBoolean;
import com.lukflug.panelstudio.setting.ILabeled;
import me.srgantmoomoo.postman.module.Module;

public class Setting implements ILabeled {
    private String name;
    private Module parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null; //TODO null
    }

    @Override
    public IBoolean isVisible() {
        return ()->true;
    }
}
