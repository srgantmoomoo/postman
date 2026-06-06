package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class NoSlow extends Module {
    public BooleanSetting food = new BooleanSetting("food", this, true);

    public NoSlow() {
        super("noSlow", "slow? no.", Category.MOVEMENT, 0);
        this.addSettings(food);
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (food.isEnabled() && mc.player.isUsingItem() && !mc.player.hasVehicle()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * 5.0, vel.y, vel.z * 5.0);
        }
    }
}
