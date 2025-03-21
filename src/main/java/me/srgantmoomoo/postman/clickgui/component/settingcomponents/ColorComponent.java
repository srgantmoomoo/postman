package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.CategoryRect;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.ColorSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

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
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, input.substring(0, 16),
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
}
