package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

import java.awt.*;

public class Coordinates extends HudModule {
    public BooleanSetting overworld = new BooleanSetting("overworld", this, true);
    public BooleanSetting nether = new BooleanSetting("nether", this, false);
    int width;

    public Coordinates() {
        super("coordinates", "view your coordinates", 2, 2, Category.HUD);
        this.addSettings(overworld, nether);
    }

    @Override
    public void draw(DrawContext context) {
        String overworldCoords = Formatting.GRAY + "(x)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getX()) +
                Formatting.GRAY + "(y)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getY()) +
                Formatting.GRAY + "(z)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getZ());
        String netherCoords = Formatting.RED + "(x)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getX() * 8f) +
                Formatting.RED + "(y)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getY()) +
                Formatting.RED + "(z)" + Formatting.WHITE + String.format("%.1f", MinecraftClient.getInstance().player.getPos().getZ() * 8f);

        width = MinecraftClient.getInstance().textRenderer.getWidth(netherCoords);

        if(this.overworld.isEnabled()) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, overworldCoords, getX(), getY(), 0xffffffff);
        }
        if(this.nether.isEnabled()) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, netherCoords, getX(), getY() + 10, 0xffffffff);
        }

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
        return 20;
    }
}
