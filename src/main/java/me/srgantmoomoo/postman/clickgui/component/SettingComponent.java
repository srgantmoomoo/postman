package me.srgantmoomoo.postman.clickgui.component;

import me.srgantmoomoo.postman.module.setting.Setting;
import net.minecraft.client.gui.DrawContext;

public abstract class SettingComponent {
    private Setting setting;
    private ModuleComponent moduleComponent;
    private int yOffset;
    private int x;
    private int y;
    private int color;

    public SettingComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y, int color) {
        this.setting = setting;
        this.moduleComponent = moduleComponent;
        this.yOffset = yOffset;
        this.x = x;
        this.y = y + yOffset;
        this.color = color;
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

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void drawComponent(DrawContext context) {}

    public void updateComponent(double mouseX, double mouseY) {} // i'll have to figure this out later, its too slow

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void keyTyped(int key) {}

    public boolean isMouseWithinComponent(double mouseX, double mouseY, int width, int height) {
        return mouseX > this.getX() && mouseX < this.getX() + width &&
                mouseY > this.getY() && mouseY < this.getY() + height;
    }
}
