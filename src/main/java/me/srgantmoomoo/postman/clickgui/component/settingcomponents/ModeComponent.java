package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

public class ModeComponent extends SettingComponent {
    public ModeComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        super(setting, moduleComponent, yOffset, x, y);
    }

    @Override
    public void drawComponent(DrawContext context) {
        context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getComponentColor());

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,
                this.getSetting().getName() + Formatting.GRAY + " " + ((ModeSetting) this.getSetting()).getMode(),
                this.getX() + 2, this.getY() + 2, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(this.isMouseWithinComponent(mouseX, mouseY)) {
            ((ModeSetting) this.getSetting()).cycle();
        }
    }
}
