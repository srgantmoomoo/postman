package me.srgantmoomoo.postman.module;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.clickgui.HudEditorScreen;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventKeyPress;
import me.srgantmoomoo.postman.module.modules.client.ClickGui;
import me.srgantmoomoo.postman.module.modules.client.HudEditor;
import me.srgantmoomoo.postman.module.modules.movement.*;
import me.srgantmoomoo.postman.module.modules.player.*;
import me.srgantmoomoo.postman.module.modules.render.*;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // player
        modules.add(new AutoDisconnect());
        modules.add(new AutoMine());
        modules.add(new AutoReconnect());
        modules.add(new AutoRespawn());
        modules.add(new ChestStealer());
        modules.add(new Freecam());
        modules.add(new GuiMove());
        modules.add(new Jesus());
        modules.add(new NoFall());
        modules.add(new NoKnockback());
        modules.add(new NoPush());
        modules.add(new PlayerClone());
        modules.add(new Protester());
        modules.add(new QuickPlace());
        modules.add(new QuickThrow());
        modules.add(new Refill());

        // movement
        modules.add(new AutoWalk());
        modules.add(new Fly());
        modules.add(new NoSlow());
        modules.add(new ReverseStep());
        modules.add(new SafeWalk());
        modules.add(new Sneak());
        modules.add(new Speed());
        modules.add(new Sprint());

        // pvp

        // exploits

        // render
        modules.add(new FullBright());
        //modules.add(new VibrantShader());

        // client
        modules.add(new ClickGui());
        modules.add(new HudEditor());
        //modules.add(new Example());

        // bot

        // hud
        modules.addAll(Main.INSTANCE.hudManager.hudModules);
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
            if(MinecraftClient.getInstance().currentScreen == null) {
                modules.stream().filter(m -> m.getKey() == ((EventKeyPress) e).getKey()).forEach(Module::toggle);
            }else if(MinecraftClient.getInstance().currentScreen instanceof ClickGuiScreen ||
                    MinecraftClient.getInstance().currentScreen instanceof HudEditorScreen) {
                if (getModuleByName("clickGui").getKey() == ((EventKeyPress) e).getKey())
                    getModuleByName("clickGui").toggle();
                if (getModuleByName("hudEditor").getKey() == ((EventKeyPress) e).getKey())
                    getModuleByName("hudEditor").toggle();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
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
