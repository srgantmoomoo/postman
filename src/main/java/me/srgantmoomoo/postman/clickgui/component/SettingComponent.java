package me.srgantmoomoo.postman.clickgui.component;

import me.srgantmoomoo.postman.module.setting.Setting;
import net.minecraft.client.gui.DrawContext;

public abstract class SettingComponent {
    private Setting setting;
    private ModuleComponent moduleComponent;
    private int x;
    private int y;

    public SettingComponent(Setting setting, ModuleComponent moduleComponent, int x, int y) {
        this.setting = setting;
        this.moduleComponent = moduleComponent;
        this.x = x;
        this.y = y;
    }

    public Setting getSetting() {
        return setting;
    }

    public ModuleComponent getModuleComponent() {
        return moduleComponent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawComponent(DrawContext context) {}

    public void updateComponent(double mouseX, double mouseY) {}

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void keyTyped(int key) {}

    public void closeAllSub() {}
}
