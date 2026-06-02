package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

public class Fps extends HudModule {
    int width;

    public Fps() {
        super("fps", "view your fps.", 2, 22, Category.HUD);
        this.addSettings();
    }

    @Override
    public void draw(DrawContext context) {
        String fpsString = Formatting.GRAY + "fps " + Formatting.RESET + MinecraftClient.getInstance().getCurrentFps();

        width = MinecraftClient.getInstance().textRenderer.getWidth(fpsString);

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, fpsString, getX(), getY(), 0xffffffff);

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
