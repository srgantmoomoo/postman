package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
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
    private int dragX;
    private int dragY;

    public CategoryRect(Category category, int x, int y, int width, int height,
                        boolean open, boolean dragging, int dragX, int dragY) {
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
            ModuleComponent moduleComponent = new ModuleComponent(module, this, this.x, this.y + moduleYOffset);
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
        return x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isDragging() {
        return dragging;
    }

    public int getDragX() {
        return dragX;
    }

    public int getDragY() {
        return dragY;
    }

    public void draw(DrawContext context) {
        context.fill(x, y, x + getWidth(), y + getHeight(), 0xffe6ab17);

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getCategory().getName(),
                this.getX() + 2, this.getY() + this.getHeight() / 2 -
                        MinecraftClient.getInstance().textRenderer.fontHeight / 2, -1);

        if(this.isOpen()) {
            for(ModuleComponent moduleComponent : this.getModuleComponents()) {
                moduleComponent.drawComponent(context);
            }
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.getX() && x <= this.getX() + this.getWidth() && y >= this.getY() && y <= this.getY() +
                this.getHeight();
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging()) {
            this.setX(mouseX - this.getDragX());
            this.setY(mouseY - this.getDragY());
        }
    }
}
