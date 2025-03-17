package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.Main;
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
            //categoryRect.updatePosition(mouseX, mouseY);
            categoryRect.draw(context);
        }
    }
}
