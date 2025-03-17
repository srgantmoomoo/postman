package me.srgantmoomoo.postman.clickgui.component;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.CategoryRect;
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
    private int x;
    private int y;
    private int color;
    private boolean open;
    private boolean hovered;
    private int mousex;
    private int mousey;

    public ModuleComponent(Module module, CategoryRect categoryRect, int x, int y, int color) {
        this.module = module;
        this.categoryRect = categoryRect;
        this.settingComponents = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.color = color;
        this.open = false;

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
        /*this.settingComponents.add(new KeybindComponent((KeybindSetting) setting, this, this.x,
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return this.color;
    }

    public boolean isOpen() {
        return open;
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
}
