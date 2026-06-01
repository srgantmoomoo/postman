package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventRender2d;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import me.srgantmoomoo.postman.module.Module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListModule extends HudModule {
    public ColorSetting solidColor = new ColorSetting("color", this, new Color(121, 193, 255, 255), false);
    public BooleanSetting coolRainbow = new BooleanSetting("coolRainbow", this, true);
    public BooleanSetting forgeHax = new BooleanSetting("forgeHaxStyle", this, false);

    public ArrayListModule() {
        super("arrayList", "shows you all ur enabled modules.", 2, 2, Category.HUD);
        this.addSettings(solidColor, coolRainbow, forgeHax);
    }
    private ArrayList<Module> mods = new ArrayList<>();

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventRender2d) {
            if(mods.isEmpty()) mods.addAll(Main.INSTANCE.moduleManager.getModules());

            int screenWidth = ((EventRender2d) e).context.getScaledWindowWidth();
            TextRenderer tr = MinecraftClient.getInstance().textRenderer;

            final int[] counter = {1};
            int y = 0;
            for (Module module : mods) {
                if(!module.isModuleEnabled())
                    continue;

                if (forgeHax.isEnabled()) {
                    ((EventRender2d) e).context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName() + "<",
                            screenWidth - tr.getWidth(module.getName() + "<") + - 2, y + 2,
                            this.coolRainbow.isEnabled() ? rainbow(counter[0] * 300) : solidColor.getValue().getRGB());
                }else {
                    ((EventRender2d) e).context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName(),
                            screenWidth - tr.getWidth(module.getName()) - 2, y + 2,
                            this.coolRainbow.isEnabled() ? rainbow(counter[0] * 300) : solidColor.getValue().getRGB());
                }
                y += tr.fontHeight;
                counter[0]++;
            }
            mods.sort(Comparator.comparing(module -> -MinecraftClient.getInstance().textRenderer.getWidth(module.getName())));
        }
    }

    private int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= -360;
        return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
    }

}