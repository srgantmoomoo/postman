package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ClickGuiScreen extends Screen {
    private ArrayList<CategoryRect> categoryRects;
    private boolean mouseHeld = false;
    Setting categoryColor = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("categoryColor");

    public ClickGuiScreen() {
        super(Text.literal("clickGui"));
        categoryRects = new ArrayList<>();
        int rectX = 10;
        int rectY = 15;
        int rectWidth = 88; // 88, 12
        int rectHeight = 12;

        for(Category category : Category.values()) {
            categoryRects.add(new CategoryRect(category, rectX, rectY, rectWidth, rectHeight,
                    ((ColorSetting) categoryColor).toInteger(), true, false, 0, 0));
            rectX += rectWidth + 1;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        for(CategoryRect categoryRect : categoryRects) {
            categoryRect.updatePosition(mouseX, mouseY);
            categoryRect.draw(context);
            for(ModuleComponent compo : categoryRect.getModuleComponents()) {
                compo.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(CategoryRect rect : categoryRects) {
            if(rect.isWithinRect(mouseX, mouseY) && button == 0) {
                rect.setDragging(true);
                rect.setDragX(mouseX - rect.getX());
                rect.setDragY(mouseY - rect.getY());
            }else if(rect.isWithinRect(mouseX, mouseY) && button == 1) {
                rect.setOpen(!rect.isOpen());
            }/*else if(rect.isOpen()) { // module interactions need to be put in here?? mouse clicked
                for(ModuleComponent compo : rect.getModuleComponents()) {
                    compo.updateComponent(mouseX, mouseY);
                }
            }*/

            /*if(rect.isOpen()) {
                for(ModuleComponent compo : rect.getModuleComponents()) {
                    // compo.mouseClicked
                }
            }*/
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(CategoryRect rect : categoryRects) {
            if(rect.isWithinRect(mouseX, mouseY) && button == 0) {
                rect.setDragging(false);
            }

            /*if(rect.isOpen()) {
                for(ModuleComponent compo : rect.getModuleComponents()) {
                    //compo.mouseReleased
                }
            }*/
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }
}
