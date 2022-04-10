package me.srgantmoomoo.postman.impl.modules.render;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.renderer.ItemRenderer;
import org.lwjgl.input.Keyboard;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class LowOffHand extends Module {
    public NumberSetting lowness = new NumberSetting("lowness", this, 0.7, 0, 1, 0.1);
    private static final ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;

    public LowOffHand() {
        super("lowOffHand", "lowers the offhand.", Keyboard.KEY_NONE, Category.RENDER);
        this.addSettings(lowness);
    }

    @Override
    public void onUpdate() {
        itemRenderer.equippedProgressOffHand = (float) lowness.getValue();
    }
}
