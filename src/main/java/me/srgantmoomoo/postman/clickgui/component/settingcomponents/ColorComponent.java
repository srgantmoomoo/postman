package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.clickgui.CategoryRect;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ColorComponent extends SettingComponent {
    private boolean hovered = false;
    private boolean typing = false;
    private String input = "";
    private ColorSetting setting = (ColorSetting) this.getSetting();
    private boolean rainbow = setting.getRainbow();
    //TODO abstract things like settingColor, ((BooleanSetting) setting), Minecraft.getInstance, etc...

    public ColorComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        super(setting, moduleComponent, yOffset, x, y);
    }

    CategoryRect catRect = this.getModuleComponent().getCategoryRect();
    @Override
    public void drawComponent(DrawContext context) {
        context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getComponentColor());

        if(typing) {
            if(input.isEmpty()) {
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,
                        Formatting.GRAY + "rrr ggg bbb aaa ...", this.getX() + 2, this.getY() + 2, -1);
            }else if(input.length() >= 15) {
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, input.substring(0, 15),
                        this.getX() + 2, this.getY() + 2, this.getSettingColor());
            }else {
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, input + Formatting.GRAY + " ...",
                        this.getX() + 2, this.getY() + 2,  this.getSettingColor());
            }
        }else {
            if(hovered) {
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "" + Formatting.GRAY +
                        this.setting.getValue().getRed() + " " + this.setting.getValue().getGreen() + " " +
                        this.setting.getValue().getBlue() + " " + this.setting.getValue().getAlpha(),
                        this.getX() + 2, this.getY() + 2, -1);
            }else {
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getSetting().getName(),
                        this.getX() + 2, this.getY() + 2, -1);
                context.fill(this.getX() + catRect.getWidth() - 10, this.getY() + catRect.getHeight() - 9,
                        this.getX() + catRect.getWidth() - 4, this.getY() + catRect.getHeight() - 3, this.setting.getValue().getRGB());
            }
        }
    }

    @Override
    public void updateComponent(double mouseX, double mouseY) {
        this.hovered = this.isMouseWithinComponent(mouseX, mouseY);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(this.isMouseWithinComponent(mouseX, mouseY)) {
            if(button == 0) {
                this.typing = !this.typing;
                this.input = "";
            }else if(button == 1) {
                this.rainbow = !this.rainbow;
                ((ColorSetting) this.getSetting()).setRainbow(this.rainbow);
            }
        }else {
            this.typing = false;
        }
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.typing) {
            if(keyCode == GLFW.GLFW_KEY_ENTER) {
                if(input.length() >= 15) {
                    int valR = Integer.parseInt(input.substring(0, 3));
                    int valG = Integer.parseInt(input.substring(4, 7));
                    int valB = Integer.parseInt(input.substring(8, 11));
                    int valA = Integer.parseInt(input.substring(12, 15));

                    if(valR > 255) valR = 255;
                    if(valG > 255) valG = 255;
                    if(valB > 255) valB = 255;
                    if(valA > 255) valA = 255;

                    try {
                        this.setting.setValue(new Color(valR, valG, valB, valA));
                        this.typing = false;
                    } catch (Exception invalid) {
                        //TODO notifications... invalid!
                    }
                    input = "";
                    return;
                }
            }

            String keyPressed = "";
            if(keyCode == GLFW.GLFW_KEY_0 || keyCode == GLFW.GLFW_KEY_1 || keyCode == GLFW.GLFW_KEY_2 ||
                    keyCode == GLFW.GLFW_KEY_3 || keyCode == GLFW.GLFW_KEY_4 || keyCode == GLFW.GLFW_KEY_5 ||
                    keyCode == GLFW.GLFW_KEY_6 || keyCode == GLFW.GLFW_KEY_7 || keyCode == GLFW.GLFW_KEY_8 ||
                    keyCode == GLFW.GLFW_KEY_9 || keyCode == GLFW.GLFW_KEY_SPACE || keyCode == GLFW.GLFW_KEY_BACKSPACE) {
                // space
                if(keyCode == GLFW.GLFW_KEY_SPACE) {
                    keyPressed = " ";
                }
                // backspace
                else if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
                    if(!input.isEmpty())
                        input = input.substring(0, input.length() - 1);
                }
                // number keys
                else keyPressed = GLFW.glfwGetKeyName(keyCode, GLFW.glfwGetKeyScancode(keyCode));
            }
            this.input += keyPressed;
        }
    }
}
