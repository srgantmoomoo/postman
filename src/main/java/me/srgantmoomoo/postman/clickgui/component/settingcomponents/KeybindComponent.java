package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.KeybindSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class KeybindComponent extends SettingComponent {
    private boolean isBinding = false;

    public KeybindComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        super(setting, moduleComponent, yOffset, x, y);
    }

    private String getKeyName(int key, int scancode) {
        return switch(key) {
            case GLFW.GLFW_KEY_TAB -> "tab";
            case GLFW.GLFW_KEY_ENTER -> "enter";
            case GLFW.GLFW_KEY_BACKSPACE -> "backspace";
            case GLFW.GLFW_KEY_DELETE -> "delete";
            case GLFW.GLFW_KEY_ESCAPE -> "escape";
            case GLFW.GLFW_KEY_UNKNOWN -> "unknown";
            case GLFW.GLFW_KEY_GRAVE_ACCENT -> "grace accent";
            case GLFW.GLFW_KEY_WORLD_1 -> "world 1";
            case GLFW.GLFW_KEY_WORLD_2 -> "world 2";
            case GLFW.GLFW_KEY_PRINT_SCREEN -> "print screen";
            case GLFW.GLFW_KEY_PAUSE -> "pause";
            case GLFW.GLFW_KEY_INSERT -> "insert";
            case GLFW.GLFW_KEY_HOME -> "home";
            case GLFW.GLFW_KEY_PAGE_UP -> "page up";
            case GLFW.GLFW_KEY_PAGE_DOWN -> "page down";
            case GLFW.GLFW_KEY_END -> "end";
            case GLFW.GLFW_KEY_LEFT_CONTROL -> "left control";
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> "right control";
            case GLFW.GLFW_KEY_LEFT_ALT -> "left alt";
            case GLFW.GLFW_KEY_RIGHT_ALT -> "right alt";
            case GLFW.GLFW_KEY_LEFT_SHIFT -> "left shift";
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> "right shift";
            case GLFW.GLFW_KEY_UP -> "up arrow";
            case GLFW.GLFW_KEY_DOWN -> "down arrow";
            case GLFW.GLFW_KEY_LEFT -> "left arrow";
            case GLFW.GLFW_KEY_RIGHT -> "right arrow";
            case GLFW.GLFW_KEY_APOSTROPHE -> "apostrophe";
            case GLFW.GLFW_KEY_CAPS_LOCK -> "capslock";
            case GLFW.GLFW_KEY_MENU -> "menu";
            case GLFW.GLFW_KEY_LEFT_SUPER -> "left super";
            case GLFW.GLFW_KEY_RIGHT_SUPER -> "right super";
            case GLFW.GLFW_KEY_KP_ENTER -> "numpad enter";
            case GLFW.GLFW_KEY_NUM_LOCK -> "num lock";
            case GLFW.GLFW_KEY_SPACE -> "space";
            case GLFW.GLFW_KEY_F1 -> "f1";
            case GLFW.GLFW_KEY_F2 -> "f2";
            case GLFW.GLFW_KEY_F3 -> "f3";
            case GLFW.GLFW_KEY_F4 -> "f4";
            case GLFW.GLFW_KEY_F5 -> "f5";
            case GLFW.GLFW_KEY_F6 -> "f6";
            case GLFW.GLFW_KEY_F7 -> "f7";
            case GLFW.GLFW_KEY_F8 -> "f8";
            case GLFW.GLFW_KEY_F9 -> "f9";
            case GLFW.GLFW_KEY_F10 -> "f10";
            case GLFW.GLFW_KEY_F11 -> "f11";
            case GLFW.GLFW_KEY_F12 -> "f12";
            case GLFW.GLFW_KEY_F13 -> "f13";
            case GLFW.GLFW_KEY_F14 -> "f14";
            case GLFW.GLFW_KEY_F15 -> "f15";
            case GLFW.GLFW_KEY_F16 -> "f16";
            case GLFW.GLFW_KEY_F17 -> "f17";
            case GLFW.GLFW_KEY_F18 -> "f18";
            case GLFW.GLFW_KEY_F19 -> "f19";
            case GLFW.GLFW_KEY_F20 -> "f20";
            case GLFW.GLFW_KEY_F21 -> "f21";
            case GLFW.GLFW_KEY_F22 -> "f22";
            case GLFW.GLFW_KEY_F23 -> "f23";
            case GLFW.GLFW_KEY_F24 -> "f24";
            case GLFW.GLFW_KEY_F25 -> "f25";
            default -> GLFW.glfwGetKeyName(key, scancode);
        };
    }

    @Override
    public void drawComponent(DrawContext context) {
        context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getComponentColor());

        if(this.isBinding) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "listening " +
                    Formatting.GRAY + "...", this.getX() + 2, this.getY() + 2, -1);
        }else {
            String keyName = this.getKeyName(((KeybindSetting) this.getSetting()).getKey(),
                    GLFW.glfwGetKeyScancode(((KeybindSetting) this.getSetting()).getKey()));
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, keyName == null ?
                    "bind" + Formatting.GRAY + " " + "none" : "bind" + Formatting.GRAY + " " + keyName,
                    this.getX() + 2, this.getY() + 2, -1);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(this.isMouseWithinComponent(mouseX, mouseY)) {
            if(button == 0) {
                this.isBinding = !this.isBinding;
            }else if(button == 1) {
                ((KeybindSetting) this.getSetting()).setKey(0);
                this.isBinding = false;
            }
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.isBinding) {
            ((KeybindSetting) this.getSetting()).setKey(keyCode);
            this.isBinding = false;
        }
    }
}
