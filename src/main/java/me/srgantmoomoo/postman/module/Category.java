package me.srgantmoomoo.postman.module;

import com.lukflug.panelstudio.setting.ICategory;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Category implements ICategory {
    PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), MOVEMENT("movement"), HUD("hud"), CLIENT("client"), BOT("bot");

    private final String name;
    private final List<Module> modules = new ArrayList<Module>();

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public Stream<IModule> getModules() {
        return modules.stream().map(module->module); //TODO this
    }

    public static IClient getClient() {
        return new IClient() {
            @Override
            public Stream<ICategory> getCategories() {
                return Arrays.stream(Category.values());
            }
        };
    }

}
