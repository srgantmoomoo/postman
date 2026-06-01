package me.srgantmoomoo.postman.clickgui;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.hud.DraggableComponent;
import me.srgantmoomoo.postman.module.hud.HudModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class HudEditorScreen extends Screen {
    private static ArrayList<DraggableComponent> draggableComponents;

    public HudEditorScreen() {
        super(Text.literal("hudEditor"));
    }

    private final ManagedShaderEffect blur = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/blur" + ".json"));
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.blur.render(1);

        // pulls the rendering for each individual hud module
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.drawDraggable(context, mouseX, mouseY);
        }

        // handles hud module dragging
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().draw(context, mouseX, mouseY);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (HudModule m : Main.INSTANCE.hudManager.hudModules) {
            m.getDraggableComponent().mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }
}
