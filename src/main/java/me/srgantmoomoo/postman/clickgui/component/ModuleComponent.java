package me.srgantmoomoo.postman.clickgui.component;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.CategoryRect;
import me.srgantmoomoo.postman.clickgui.ClickGuiScreen;
import me.srgantmoomoo.postman.clickgui.component.settingcomponents.*;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.Setting;
import me.srgantmoomoo.postman.module.setting.settings.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModuleComponent {
    private Module module;
    private CategoryRect categoryRect;
    private ArrayList<SettingComponent> settingComponents;
    private int yOffset;
    private int x;
    private int y;
    private boolean open;
    private boolean hovered;

    public ModuleComponent(Module module, CategoryRect categoryRect, int yOffset, int x, int y, boolean open, boolean hovered) {
        this.module = module;
        this.categoryRect = categoryRect;
        this.settingComponents = new ArrayList<>();
        this.yOffset = yOffset;
        this.x = x;
        this.y = y + yOffset;
        this.open = open;
        this.hovered = hovered;

        // add setting components to module
        int settingYOffset = 0; // + 12??? idk why???
        if(module.getSettings() != null) {
            for(Setting setting : module.getSettings()) {
                if(setting instanceof BooleanSetting) {
                    this.settingComponents.add(new BooleanComponent((BooleanSetting) setting, this, settingYOffset, this.x + categoryRect.getWidth() + 2,
                            this.y));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof NumberSetting) {
                    this.settingComponents.add(new NumberComponent((NumberSetting) setting, this, settingYOffset, this.x + categoryRect.getWidth() + 2,
                            this.y));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof ModeSetting) {
                    this.settingComponents.add(new ModeComponent((ModeSetting) setting, this, settingYOffset, this.x + categoryRect.getWidth() + 2,
                            this.y));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof ColorSetting) {
                    this.settingComponents.add(new ColorComponent((ColorSetting) setting, this, settingYOffset, this.x + categoryRect.getWidth() + 2,
                            this.y));
                    settingYOffset += this.categoryRect.getHeight();
                }
            }
            this.settingComponents.add(new KeybindComponent(this.module.getKeybindSetting(), this, settingYOffset, this.x + categoryRect.getWidth() + 2,
                    this.y));
        }
    }

    public Module getModule() {
        return module;
    }

    public CategoryRect getCategoryRect() {
        return categoryRect;
    }

    public ArrayList<SettingComponent> getSettingComponents() {
        return settingComponents;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public int getComponentColor() {
        return ((ColorSetting) Main.INSTANCE.moduleManager.getModuleByName("clickGui")
                .getSettingByName("componentColor")).getValue().getRGB();
    }

    private void drawModuleName(DrawContext context) {
        String moduleName;
        if(this.isOpen()) moduleName = Formatting.GRAY + "... " + Formatting.RESET + this.module.getName();
        else moduleName = this.module.getName();

        if(hovered) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, moduleName, this.getX() + 2,
                    (this.getY() + 1), 0xffffffff);
        }else
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, moduleName, this.getX() + 3,
                    (this.getY() + 2), 0xffffffff);
    }

    private final Identifier check = new Identifier(Main.INSTANCE.MODID, "check.png");
    public void drawComponent(DrawContext context) {
        // module name and background
        context.fill(this.getX(), this.getY(), this.getX() + this.getCategoryRect().getWidth(),
                this.getY() + this.getCategoryRect().getHeight(), this.getComponentColor());
        this.drawModuleName(context);

        // draw check mark if enabled
        if(this.getModule().isModuleEnabled()) {
            RenderSystem.setShaderTexture(0, check);
            context.drawTexture(check, getX() + this.getCategoryRect().getWidth() - 13, (this.getY() + 1),
                    10, 10, 0, 0, 10, 10, 10, 10);
        }

        // draw setting components
        if(this.isOpen()) {
            for (SettingComponent compo : this.getSettingComponents()) {
                compo.drawComponent(context);
            }
        }
    }

    private boolean isMouseWithinComponent(double mouseX, double mouseY) {
        return mouseX > this.getX() && mouseX < this.getX() + this.getCategoryRect().getWidth() &&
                mouseY > this.getY() && mouseY < this.getY() + this.getCategoryRect().getHeight();
    }

    public void updateComponent(double mouseX, double mouseY) {
        this.setHovered(this.isMouseWithinComponent(mouseX, mouseY));
        // changing module positions in here is obscenely slow.
        //TODO if onwall; x = ...; y = ...;
        for(SettingComponent compo : this.getSettingComponents()) {
            compo.updateComponent(mouseX, mouseY);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseWithinComponent(mouseX, mouseY)) {
            if(button == 0) {
                this.getModule().toggle();
            }else if(button == 1) {
                if(!this.isOpen()) {
                    ClickGuiScreen.closeAllSettingComponents();
                    this.setOpen(true);
                }else {
                    this.setOpen(false);
                }
            }
        }

        if(this.isOpen()) {
            for(SettingComponent compo : this.getSettingComponents()) {
                compo.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(this.isOpen()) {
            this.getSettingComponents().forEach(compo -> compo.mouseReleased(mouseX, mouseY, button));
        }
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if(this.isOpen()) {
            this.getSettingComponents().forEach(compo -> compo.keyPressed(keyCode, scanCode, modifiers));
        }
    }
}
