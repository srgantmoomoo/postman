package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;

public class CategoryRect {
    private Category category;
    private ArrayList<ModuleComponent> moduleComponents;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean open;
    private boolean dragging;
    private double dragX;
    private double dragY;;

    public CategoryRect(Category category, int x, int y, int width, int height, boolean open,
                        boolean dragging, float dragX, float dragY) {
        this.category = category;
        this.moduleComponents = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.open = open;
        this.dragging = dragging;
        this.dragX = dragX;
        this.dragY = dragY;

        // add module componenets to category
        int moduleYOffset = this.height;
        for(Module module : Main.INSTANCE.moduleManager.getModulesInCategory(category)) {
            ModuleComponent moduleComponent = new ModuleComponent(module, this, moduleYOffset, this.x, this.y, false, false);
            this.moduleComponents.add(moduleComponent);
            moduleYOffset += this.height;
        }
    }

    public Category getCategory() {
        return this.category;
    }

    public ArrayList<ModuleComponent> getModuleComponents() {
        return this.moduleComponents;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public double getDragX() {
        return dragX;
    }

    public void setDragX(double dragX) {
        this.dragX = dragX;
    }

    public double getDragY() {
        return dragY;
    }

    public void setDragY(double dragY) {
        this.dragY = dragY;
    }

    public int getCategoryColor() {
        return ((ColorSetting) Main.INSTANCE.moduleManager.getModuleByName("clickGui")
                .getSettingByName("categoryColor")).getValue().getRGB();
    }

    public void draw(DrawContext context) {
        context.fill(x, y, x + getWidth(), y + getHeight(), this.getCategoryColor());

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getCategory().getName(),
                this.getX() + 2, this.getY() + this.getHeight() / 2 -
                        MinecraftClient.getInstance().textRenderer.fontHeight / 2, -1);

        if(this.isOpen()) {
            for(ModuleComponent moduleComponent : this.getModuleComponents()) {
                moduleComponent.drawComponent(context);
            }
        }
    }

    public boolean isWithinRect(double x, double y) {
        return x >= this.getX() && x <= this.getX() + this.getWidth() && y >= this.getY() && y <= this.getY() +
                this.getHeight();
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging()) {
            this.setX((int)(mouseX - this.getDragX()));
            this.setY((int)(mouseY - this.getDragY()));
            for(ModuleComponent compo : this.getModuleComponents()) {
                compo.setX(this.getX());
                compo.setY(this.getY() + compo.getYOffset());
                for(SettingComponent setCompo : compo.getSettingComponents()) {
                    setCompo.setX(this.getX() + this.getWidth() + 2);
                    setCompo.setY(this.getY() + compo.getYOffset() + setCompo.getYOffset());
                }
            }
        }
    }
}
