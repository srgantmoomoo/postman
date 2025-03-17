package me.srgantmoomoo.postman.clickgui;

import me.srgantmoomoo.postman.module.Category;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ClickGuiScreen extends Screen {
    private ArrayList<CategoryRect> categoryRects;
    private boolean mouseHeld = false;

    public ClickGuiScreen() {
        super(Text.literal("clickGui"));
        categoryRects = new ArrayList<>();
        int rectX = 10;
        int rectY = 15;
        int rectWidth = 88;
        int rectHeight = 12;

        for(Category category : Category.values()) {
            categoryRects.add(new CategoryRect(category, rectX, rectY, rectWidth, rectHeight, false, false, 0, 0));
            rectX += rectWidth + 1;
        }
    }
}
