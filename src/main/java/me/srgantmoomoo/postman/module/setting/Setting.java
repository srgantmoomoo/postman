package me.srgantmoomoo.postman.module.setting;

import me.srgantmoomoo.postman.module.Module;

public class Setting {
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
}
