package me.srgantmoomoo.postman.module.modules.hud;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.hud.HudModule;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

public class Ping extends HudModule {
    public BooleanSetting colorful = new BooleanSetting("colorful", this, false);
    int width;

    public Ping() {
        super("ping", "view your ping.", 2, 32, Category.HUD);
        this.addSettings(colorful);
    }

    @Override
    public void draw(DrawContext context) {
        String pingString;
        if(MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().getNetworkHandler() == null) return;

        int ping = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(MinecraftClient.getInstance().player.getUuid()).getLatency();
        if(this.colorful.isEnabled()) {
            if(ping <= 20)
                pingString = Formatting.GRAY + "ping " + Formatting.GREEN + ping;
            else if(ping <= 60)
                pingString = Formatting.GRAY + "ping " + Formatting.WHITE + ping;
            else if(ping <= 100)
                pingString = Formatting.GRAY + "ping " + Formatting.YELLOW + ping;
            else
                pingString = Formatting.GRAY + "ping " + Formatting.RED + ping;
        } else pingString = Formatting.GRAY + "ping " + Formatting.WHITE + ping;

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, pingString, getX(), getY(), 0xffffffff);

        width = MinecraftClient.getInstance().textRenderer.getWidth(pingString);
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
