package me.srgantmoomoo.postman.module;

import com.lukflug.panelstudio.setting.ICategory;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IModule;
import me.srgantmoomoo.postman.Main;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Category {
    PLAYER("player"), MOVEMENT("movement"), PVP("pvp"), EXPLOITS("exploits"), RENDER("render"), HUD("hud"), CLIENT("client"), BOT("bot");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
