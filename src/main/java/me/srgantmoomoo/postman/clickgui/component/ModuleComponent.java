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
    private int color;
    private boolean open;
    private boolean hovered;
    private int mousex;
    private int mousey;

    public ModuleComponent(Module module, CategoryRect categoryRect, int yOffset, int x, int y, int color, boolean open,
                           boolean hovered) {
        this.module = module;
        this.categoryRect = categoryRect;
        this.settingComponents = new ArrayList<>();
        this.yOffset = yOffset;
        this.x = x;
        this.y = y + yOffset;
        this.color = color;
        this.open = open;
        this.hovered = hovered;

        // add setting components to module
        int settingYOffset = this.categoryRect.getHeight(); // + 12??? idk why???
        if(module.getSettings() != null) {
            for(Setting setting : module.getSettings()) {
                if(setting instanceof BooleanSetting) {
                    this.settingComponents.add(new BooleanComponent((BooleanSetting) setting, this, this.x,
                            this.y + settingYOffset));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof NumberSetting) {
                    this.settingComponents.add(new NumberComponent((NumberSetting) setting, this, this.x,
                            this.y + settingYOffset));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof ModeSetting) {
                    this.settingComponents.add(new ModeComponent((ModeSetting) setting, this, this.x,
                            this.y + settingYOffset));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof ColorSetting) {
                    this.settingComponents.add(new ColorComponent((ColorSetting) setting, this, this.x,
                            this.y + settingYOffset));
                    settingYOffset += this.categoryRect.getHeight();
                }
                if(setting instanceof KeybindSetting) {
                    this.settingComponents.add(new KeybindComponent((KeybindSetting) setting, this, this.x,
                            this.y + settingYOffset));
                }
            }
        }
        /*this.settingComponents.add(new KeybindComponent(null, this, this.x,
                this.y + settingYOffset));*/
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

    public int getColor() {
        return this.color;
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

    // using this method to draw module names with "..." AND some other things like hovering.
    private void drawModuleName(DrawContext context) {
        String shortName = this.getModule().getName();

        if(shortName.length() > 12) {
            shortName = shortName.substring(0, 10) + Formatting.GRAY + " ...";
        }

        if(hovered) {
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getModule().getName(),
                    this.getX() + 2, (this.getY() + 1), 0xffffffff);
        }else
            context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getModule().isModuleEnabled() ?
                    shortName : this.getModule().getName(), this.getX() + 3, (this.getY() + 2), 0xffffffff);
    }

    private final Identifier check = new Identifier(Main.INSTANCE.MODID, "check.png");
    public void drawComponent(DrawContext context) {
        // module name and background
        context.fill(this.getX(), this.getY(), this.getX() + this.getCategoryRect().getWidth(),
                this.getY() + this.getCategoryRect().getHeight(), this.getColor());
        this.drawModuleName(context);

        // draw check mark if enabled
        if(this.getModule().isModuleEnabled()) {
            RenderSystem.setShaderTexture(0, check);
            context.drawTexture(check, getX() + this.getCategoryRect().getWidth() - 13, (this.getY() + 1),
                    10, 10, 0, 0, 10, 10, 10, 10);
        }

        // draw setting components
        if(this.isOpen() && !this.settingComponents.isEmpty()) {
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
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseWithinComponent(mouseX, mouseY)) {
            if(button == 0) {
                this.getModule().toggle();
            }else if(button == 1) {
                ClickGuiScreen.closeAllSettingComponents();
                this.setOpen(!this.isOpen());
            }
        }

        if(this.isOpen()) {
            for(SettingComponent compo : this.getSettingComponents()) {
                compo.mouseClicked(mouseX, mouseY, button);
            }
        }
    }
}
