package me.srgantmoomoo.postman.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // instantiate modules
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
