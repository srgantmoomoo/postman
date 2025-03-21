package me.srgantmoomoo.postman.clickgui;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ClickGuiScreen extends Screen {
    private static ArrayList<CategoryRect> categoryRects;
    Setting background = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("background");
    Setting pauseGame = Main.INSTANCE.moduleManager.getModuleByName("clickGui").getSettingByName("pauseGame");

    public ClickGuiScreen() {
        super(Text.literal("clickGui"));
        categoryRects = new ArrayList<>();
        int rectX = 10;
        int rectY = 15;
        int rectWidth = 100; // 88, 12
        int rectHeight = 12;

        for(Category category : Category.values()) {
            categoryRects.add(new CategoryRect(category, rectX, rectY, rectWidth, rectHeight, true, false, 0, 0));
            rectX += rectWidth + 1;
        }
    }

    public static void closeAllSettingComponents() {
        for(CategoryRect categoryRect : categoryRects) {
            categoryRect.getModuleComponents().forEach(compo -> compo.setOpen(false));
        }
    }

    private final Identifier postmanLogo = new Identifier(Main.INSTANCE.MODID, "postman-logo-transparent.png");
    private final ManagedShaderEffect blur = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/blur" + ".json"));
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // background
        if(((ModeSetting) background).is("blur"))
            this.blur.render(1);
        else if(((ModeSetting) background).is("dim"))
            this.renderBackground(context);

        // postman logo
        context.drawTexture(postmanLogo, 0, MinecraftClient.getInstance().getWindow().getScaledHeight() - 80,
                0, 0, 80, 80, 80, 80);

        // categories & modules
        for(CategoryRect categoryRect : categoryRects) {
            categoryRect.updatePosition(mouseX, mouseY);
            categoryRect.draw(context);
            for(ModuleComponent compo : categoryRect.getModuleComponents()) { //TODO moving this into catRect would probably work better
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

            if(rect.isOpen()) {
                for(ModuleComponent compo : rect.getModuleComponents()) {
                    compo.mouseClicked(mouseX, mouseY, button);
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(CategoryRect rect : categoryRects) {
            if(rect.isWithinRect(mouseX, mouseY) && button == 0) {
                rect.setDragging(false);
            }

            if(rect.isOpen()) {
                for(ModuleComponent compo : rect.getModuleComponents()) {
                    compo.mouseReleased(mouseX, mouseY, button);
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for(CategoryRect rect : categoryRects) {
            if(rect.isOpen()) {
                rect.getModuleComponents().forEach(compo -> compo.keyPressed(keyCode, scanCode, modifiers));
            }
        }

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) { //TODO clickgui esc close option
            this.close();
            return true;
        }
        return false;
    }

    /*
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }
    */

    @Override
    public boolean shouldPause() {
        return ((BooleanSetting) this.pauseGame).isEnabled();
    }
}
