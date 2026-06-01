package me.srgantmoomoo.postman.module.hud;

import net.minecraft.client.gui.DrawContext;

public class DraggableComponent {
    private HudModule hudModule;
    private int x;
    private int y;
    private int width;
    private int height;
    private int lastX;
    private int lastY;

    private boolean dragging = false;

    public DraggableComponent(HudModule hudModule, int x, int y, int width, int height) {
        this.hudModule = hudModule;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public HudModule getHudModule() {
        return this.hudModule;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public void setXPos(int x) {
        this.x = x;
    }

    public void setYPos(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(DrawContext context, int mouseX, int mouseY) { // on update
        if(this.dragging) {
            this.x = (int) (mouseX + this.lastX);
            this.y = (int) (mouseY + this.lastY);
        }
    }

    private boolean isMouseWithinComponent(double mouseX, double mouseY) {
        return mouseX > this.getXPos() && mouseX < this.getXPos() + this.getWidth() &&
                mouseY > this.getYPos() && mouseY < this.getYPos() + this.getHeight();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseWithinComponent(mouseX, mouseY)) {
            if(button == 0) {
                if (!this.dragging) {
                    this.lastX = (int) (x - mouseX);
                    this.lastY = (int) (y - mouseY);
                    this.dragging = true;
                }
            }

            if(button == 1) {
                this.hudModule.toggle();
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) { // TESTING
        if (this.dragging) {
            this.dragging = false;
        }
    }
}