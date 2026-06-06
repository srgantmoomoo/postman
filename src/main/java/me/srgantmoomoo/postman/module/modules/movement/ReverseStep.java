package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Box;

public class ReverseStep extends Module {
    public NumberSetting height = new NumberSetting("height", this, 2.5, 0.5, 10, 0.5);

    public ReverseStep() {
        super("reverseStep", "sucks u down when going down a block.", Category.MOVEMENT, 0);
        this.addSettings(height);
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        if (mc.player.isTouchingWater() || mc.player.isInLava() || mc.player.isClimbing()) return;
        if (mc.options.jumpKey.isPressed()) return;

        if (mc.player.isOnGround()) {
            for (double y = 0.0; y < height.getValue() + 0.5; y += 0.01) {
                Box offset = mc.player.getBoundingBox().offset(0.0, -y, 0.0);
                if (!mc.world.isSpaceEmpty(mc.player, offset)) {
                    mc.player.setVelocity(mc.player.getVelocity().x, -10.0, mc.player.getVelocity().z);
                    break;
                }
            }
        }
    }
}