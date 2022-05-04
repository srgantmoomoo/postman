package me.srgantmoomoo.postman.impl.modules.movement;

import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.InputUpdateEvent;
import org.lwjgl.input.Keyboard;

public class NoSlow extends Module {
    public BooleanSetting food = new BooleanSetting("food", this, true);
    public BooleanSetting web = new BooleanSetting("web", this, true);
    public BooleanSetting soulSand = new BooleanSetting("soulSand", this, true);
    public BooleanSetting slimeBlock = new BooleanSetting("slimeBlock", this, true);

    @EventHandler
    private final Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
        if (mc.player.isHandActive() && !mc.player.isRiding() && food.isEnabled()) {
            event.getMovementInput().moveStrafe *= 5;
            event.getMovementInput().moveForward *= 5;
        }
    });

    public NoSlow() {
        super("noSlow", "slow? no.", Keyboard.KEY_NONE, Category.MOVEMENT);
        this.addSettings(food, web, soulSand, slimeBlock);
    }

    @Override
    public void onEnable() {
        Blocks.DIRT.setLightOpacity(10);
    }
}