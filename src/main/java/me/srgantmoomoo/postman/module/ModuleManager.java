package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import me.srgantmoomoo.postman.module.modules.ClickGuiModule;
import me.srgantmoomoo.postman.module.modules.Example;
import me.srgantmoomoo.postman.module.modules.player.NoKnockback;
import me.srgantmoomoo.postman.module.modules.player.NoPush;
import me.srgantmoomoo.postman.module.modules.player.QuickPlace;
import me.srgantmoomoo.postman.module.modules.player.QuickThrow;
import me.srgantmoomoo.postman.module.modules.render.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new ClickGuiModule());
        modules.add(new Example());
        modules.add(new FullBright());
        modules.add(new NoKnockback());
        modules.add(new NoPush());
        modules.add(new QuickPlace());
        modules.add(new QuickThrow());
        modules.add(new VibrantShader());
    }

    public void onEvent(Event e) {
        for(Module module : getModules()) {
            if(!module.isModuleEnabled())
                continue;
            module.onEvent(e);
        }
    }

    // for key binds, called in MixinKeyboard.
    public void onKeyPress(Event e, int key, int scanCode) {
        if(e instanceof EventKeyPress) {
            modules.stream().filter(m -> m.getKey() == ((EventKeyPress) e).getKey()).forEach(Module::toggle);
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for(Module module : modules) {
            if(module.getName().equalsIgnoreCase(name))
                return module;
        }
        return null;
    }

    public List<Module> getModulesInCategory(Category category) {
        List<Module> result = new ArrayList<>();

        for(Module module : modules) {
            if(module.getCategory().getName().equalsIgnoreCase(category.getName())) {
                result.add(module);
            }
        }
        return result;
    }
}
