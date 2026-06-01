package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class Watermark extends HudModule {
    public ColorSetting watermarkColor = new ColorSetting("color", this, new Color(121, 193, 255, 255), false);

    public Watermark() {
        super("watermark", "does watermark stuff.", 2, 2, Category.HUD);
        this.addSettings(watermarkColor);
    }

    @Override
    public void draw(DrawContext context) {
        Color watermarkColorRGB = watermarkColor.getValue();

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "postman", getX(), this.getY() , watermarkColorRGB.getRGB());

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
        return 44;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
