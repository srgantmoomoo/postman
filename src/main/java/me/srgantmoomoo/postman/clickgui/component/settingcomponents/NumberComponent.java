package me.srgantmoomoo.postman.clickgui.component.settingcomponents;

import me.srgantmoomoo.postman.clickgui.CategoryRect;
import me.srgantmoomoo.postman.clickgui.component.ModuleComponent;
import me.srgantmoomoo.postman.clickgui.component.SettingComponent;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComponent extends SettingComponent {
    private boolean dragging;
    private double sliderWidth;
    private NumberSetting setting = (NumberSetting) this.getSetting();

    public NumberComponent(Setting setting, ModuleComponent moduleComponent, int yOffset, int x, int y) {
        super(setting, moduleComponent, yOffset, x, y);
    }

    CategoryRect catRect = this.getModuleComponent().getCategoryRect();
    @Override
    public void drawComponent(DrawContext context) {
        context.fill(this.getX(), this.getY(), this.getX() + this.getModuleComponent().getCategoryRect().getWidth(),
                this.getY() + this.getModuleComponent().getCategoryRect().getHeight(), this.getComponentColor());
        context.fill(this.getX(), this.getY() + this.catRect.getHeight() - 1, this.getX() + (int) this.sliderWidth,
                this.getY() + this.catRect.getHeight(), this.getSettingColor());

        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.setting.getName() + " " +
                Formatting.GRAY + "<" + this.setting.getValue() + ">", this.getX() + 2, this.getY() + 2, -1);
    }

    @Override
    public void updateComponent(double mouseX, double mouseY) {
        double diff = Math.min(catRect.getWidth(), Math.max(0, mouseX - this.getX()));
        double min = this.setting.getMinimum();
        double max = this.setting.getMaximum();
        this.sliderWidth = catRect.getWidth() * (this.setting.getValue() - min) / (max - min);

        if (this.dragging) {
            if (diff == 0) {
                this.setting.setValue(this.setting.getMinimum());
            } else {
                int newValue = (int) roundToPlace(diff / catRect.getWidth() * (max - min) + min, 2);
                this.setting.setValue(newValue);
            }
        }
    }

    private double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseWithinComponent(mouseX, mouseY) || this.dragging) {
            this.dragging = true;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
    }
}
