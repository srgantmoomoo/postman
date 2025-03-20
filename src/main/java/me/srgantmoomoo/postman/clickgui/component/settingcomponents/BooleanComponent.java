package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class BooleanComponent extends SettingComponent {
    /*private BooleanSetting setting;
    private ModuleComponent moduleComponent;
    private int x;
    private int y;*/
    Setting settingColor = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("settingColor");

    public BooleanComponent(BooleanSetting setting, ModuleComponent moduleComponent, int x, int y, int color) {
        super(setting, moduleComponent, x, y, color);
    }

    @Override
    public void drawComponent(DrawContext context) {
        if(((BooleanSetting) this.getSetting()).isEnabled()) {
            context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                    this.getY() + this.getModuleComponent().getCategoryRect().getHeight(),
                    ((ColorSetting) this.settingColor).toInteger());
        }else {
            context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                    this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getColor());
        }

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getSetting().getName(),
                this.getX() + 2, this.getY() + 2, -1);
    }

    @Override
    public void updateComponent(double mouseX, double mouseY) {

    }
}