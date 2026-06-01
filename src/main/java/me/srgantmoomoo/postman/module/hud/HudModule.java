package me.srgantmoomoo.postman.module.hud;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.gui.DrawContext;

public class HudModule extends Module {
    public String name;
    public DraggableComponent drag;
    public int x, y;

    public HudModule(String name, String description, int x, int y, Category category) {
        super(name, description, category, 0);
        this.name = name;
        this.x = x;
        this.y = y;
        this.drag = new DraggableComponent(this, x, y);
    }


    // this is called in HudManager by renderMods() to draw each hud component to the hud
    public void draw(DrawContext context) {

    }

    // this is called in HudScreen to draw each component while in the hud editor
    public void drawDraggable(DrawContext context, int mouseX, int mouseY) {

    }

    public DraggableComponent getDraggableComponent() {
        return this.drag;
    }

    public int getX() {
        return drag.getXPos();
    }

    public int getY() {
        return drag.getYPos();
    }

    public int getWidth() {
        return 20;
    }

    public int getHeight() {
        return 50;
    }

    public void setX(int x) {
        drag.setXPos(x);
    }

    public void setY(int y) {
        drag.setYPos(y);
    }

}
