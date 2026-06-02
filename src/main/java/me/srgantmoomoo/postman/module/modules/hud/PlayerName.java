package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

import java.awt.*;

public class PlayerName extends HudModule {
    public ColorSetting nameColor = new ColorSetting("color", this, new Color(121, 193, 255, 255), false);
    int width;

    public PlayerName() {
        super("playerName", "displays your playername.", 2, 12, Category.HUD);
        this.addSettings(nameColor);
    }

    @Override
    public void draw(DrawContext context) {
        width = MinecraftClient.getInstance().textRenderer.getWidth(Formatting.GRAY + "hello, " + Formatting.RESET +
                MinecraftClient.getInstance().player.getDisplayName() + Formatting.GRAY + " :)");
        Color nameColorRGB = nameColor.getValue();
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, Formatting.GRAY + "hello, " + Formatting.RESET +
                MinecraftClient.getInstance().player.getName() + Formatting.GRAY + " :)", getX(), this.getY() , nameColorRGB.getRGB());

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
