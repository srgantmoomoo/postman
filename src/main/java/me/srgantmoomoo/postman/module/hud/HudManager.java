package me.srgantmoomoo.postman.module.hud;

import me.srgantmoomoo.postman.module.modules.hud.ArrayListModule;
import me.srgantmoomoo.postman.module.modules.hud.Coordinates;
import me.srgantmoomoo.postman.module.modules.hud.Fps;
import me.srgantmoomoo.postman.module.modules.hud.Watermark;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;

public class HudManager {
    public ArrayList<HudModule> hudModules = new ArrayList<>();

    public HudManager() {
        hudModules.add(new Watermark());
        hudModules.add(new ArrayListModule());
        hudModules.add(new Coordinates());
        hudModules.add(new Fps());
    }

    public HudModule getHudModule(String name) {
        for (HudModule h : this.hudModules) {
            if(h.getName().equalsIgnoreCase(name)) {
                return h;
            }
        }
        return null;
    }

    // this is called in MixinInGameHud.
    public void renderMods(DrawContext context) {
        for(HudModule m : hudModules) {
            if(m.isModuleEnabled())
                m.draw(context);
        }
    }

    public void drawBox(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x - 2, y - 2, x + width, y + height, 0x90000000);
        context.fill(x - 2, y - 2, x, y - 1, color);
        context.fill(x - 2, y - 2, x - 1, y, color);
    }
}
