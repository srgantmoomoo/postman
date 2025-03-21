package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class BooleanComponent extends SettingComponent {
    public BooleanComponent(BooleanSetting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        super(setting, moduleComponent, yOffset, x, y);
    }

    @Override
    public void drawComponent(DrawContext context) {
        if(((BooleanSetting) this.getSetting()).isEnabled()) {
            context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                    this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getSettingColor());
        }else {
            context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                    this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getComponentColor());
        }

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getSetting().getName(),
                this.getX() + 2, this.getY() + 2, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(this.isMouseWithinComponent(mouseX, mouseY)) {
            ((BooleanSetting) this.getSetting()).toggle();
        }
    }
}