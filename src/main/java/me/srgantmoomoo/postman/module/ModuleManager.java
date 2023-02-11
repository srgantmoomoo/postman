package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.event.events.EventKeyPress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // instantiate modules
    }

    // for key binds, called in MixinKeyboard.
    public void keyPress(int key, int scanCode) {
        EventKeyPress e = new EventKeyPress(key, scanCode);
        modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(Module::toggle);
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
