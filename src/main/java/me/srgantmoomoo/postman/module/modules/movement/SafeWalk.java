package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class SafeWalk extends Module {

    public SafeWalk() {
        super("safeWalk", "prevents falling off the edge of blocks.", Category.MOVEMENT, 0);
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        if (!mc.player.isOnGround() || mc.player.noClip) return;

        Vec3d vel = mc.player.getVelocity();
        double x = vel.x;
        double z = vel.z;
        double increment = 0.05;

        while (x != 0.0 && isOffsetEmpty(mc, x, -1, 0)) {
            if (Math.abs(x) < increment) x = 0;
            else x -= Math.signum(x) * increment;
        }
        while (z != 0.0 && isOffsetEmpty(mc, 0, -1, z)) {
            if (Math.abs(z) < increment) z = 0;
            else z -= Math.signum(z) * increment;
        }
        while (x != 0.0 && z != 0.0 && isOffsetEmpty(mc, x, -1, z)) {
            if (Math.abs(x) < increment) x = 0;
            else x -= Math.signum(x) * increment;
            if (Math.abs(z) < increment) z = 0;
            else z -= Math.signum(z) * increment;
        }

        mc.player.setVelocity(x, vel.y, z);
    }

    private boolean isOffsetEmpty(MinecraftClient mc, double x, double y, double z) {
        Box offset = mc.player.getBoundingBox().offset(x, y, z);
        return mc.world.isSpaceEmpty(mc.player, offset);
    }
}
