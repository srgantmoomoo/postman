package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

// TODO probably doesn't work
public class Tps extends HudModule {
    public BooleanSetting colorful = new BooleanSetting("colorful", this, false);
    int width;

    public Tps() {
        super("tps", "view your tps.", 2, 42, Category.HUD);
        this.addSettings(colorful);
    }

    @Override
    public void draw(DrawContext context) {
        if(MinecraftClient.getInstance().world == null)
            return;

        String tpsString;
        float mspt = MinecraftClient.getInstance().world.getTickManager().getMillisPerTick();
        float tps = Math.min(1000.0f / mspt, 20.0f);
        if(this.colorful.isEnabled()) {
            if(tps >= 20)
                tpsString = Formatting.GRAY + "tps " + Formatting.GREEN + String.format("%.1f", tps);
            else if(tps >= 15)
                tpsString = Formatting.GRAY + "tps " + Formatting.WHITE + String.format("%.1f", tps);
            else if(tps >= 10)
                tpsString = Formatting.GRAY + "tps " + Formatting.YELLOW + String.format("%.1f", tps);
            else
                tpsString = Formatting.GRAY + "tps " + Formatting.RED + String.format("%.1f", tps);
        } else tpsString = Formatting.GRAY + "tps " + Formatting.WHITE + String.format("%.1f", tps);

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, tpsString, getX(), getY(), 0xffffffff);

        width = MinecraftClient.getInstance().textRenderer.getWidth(tpsString);
        super.draw(context);
    }

    @Override
    public void drawDraggable(DrawContext context, int mouseX, int mouseY) {
        Main.INSTANCE.hudManager.drawBox(context, getX(), getY(), getWidth(), getHeight(), this.isModuleEnabled() ? 0xff00ff00 : 0xffffffff);
        this.draw(context);

        super.drawDraggable(context, mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        return width + 2;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}