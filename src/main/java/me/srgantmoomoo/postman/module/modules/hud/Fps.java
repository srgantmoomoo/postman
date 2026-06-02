package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

public class Fps extends HudModule {
    public BooleanSetting colorful = new BooleanSetting("colorful", this, false);
    int width;

    public Fps() {
        super("fps", "view your fps.", 2, 22, Category.HUD);
        this.addSettings(colorful);
    }

    @Override
    public void draw(DrawContext context) {
        String fpsString;
        int fps = MinecraftClient.getInstance().getCurrentFps();
        if(this.colorful.isEnabled()) {
            if(fps >= 60)
                fpsString = Formatting.GRAY + "fps " + Formatting.GREEN + fps;
            else if(fps >= 40)
                fpsString = Formatting.GRAY + "fps " + Formatting.WHITE + fps;
            else if(fps >= 20)
                fpsString = Formatting.GRAY + "fps " + Formatting.YELLOW + fps;
            else
                fpsString = Formatting.GRAY + "fps " + Formatting.RED + fps;
        } else fpsString = Formatting.GRAY + "fps " + Formatting.WHITE + fps;

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, fpsString, getX(), getY(), 0xffffffff);

        width = MinecraftClient.getInstance().textRenderer.getWidth(fpsString);
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
