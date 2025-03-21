package me.srgantmoomoo.postman.clickgui.component;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.gui.DrawContext;

public abstract class SettingComponent {
    private Setting setting;
    private ModuleComponent moduleComponent;
    private int yOffset;
    private int x;
    private int y;

    public SettingComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        this.setting = setting;
        this.moduleComponent = moduleComponent;
        this.yOffset = yOffset;
        this.x = x;
        this.y = y + yOffset;
    }

    public Setting getSetting() {
        return this.setting;
    }

    public ModuleComponent getModuleComponent() {
        return this.moduleComponent;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getComponentColor() {
        return ((ColorSetting) Main.INSTANCE.moduleManager.getModuleByName("clickGui")
                .getSettingByName("componentColor")).getValue().getRGB();
    }

    public int getSettingColor() {
        return ((ColorSetting) Main.INSTANCE.moduleManager.getModuleByName("clickGui")
                .getSettingByName("settingColor")).getValue().getRGB();
    }

    public void drawComponent(DrawContext context) {}

    public void updateComponent(double mouseX, double mouseY) {} // i'll have to figure this out later, its too slow

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void keyPressed(int keyCode, int scanCode, int modifiers) {}

    public boolean isMouseWithinComponent(double mouseX, double mouseY) {
        return mouseX > this.getX() && mouseX < this.getX() + this.getModuleComponent().getCategoryRect().getWidth() &&
                mouseY > this.getY() && mouseY < this.getY() + this.getModuleComponent().getCategoryRect().getHeight();
    }
}
