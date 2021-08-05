package me.srgantmoomoo.postman.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PlayerMoveEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;

public class SafeWalk extends Module {
	
	public SafeWalk() {
		super ("safeWalk", "prevents falling off blocks.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	@EventHandler
    private Listener<PlayerMoveEvent> OnPlayerMove = new Listener<>(event -> {
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();

        if (mc.player.onGround && !mc.player.noClip) {
            double increment;
            for (increment = 0.05D; x != 0.0D && isOffsetBBEmpty(x, -1, 0.0D); ) {
                if (x < increment && x >= -increment) {
                    x = 0.0D;
                } else if (x > 0.0D) {
                    x -= increment;
                } else {
                    x += increment;
                }
            }
            for (; z != 0.0D && isOffsetBBEmpty(0.0D, -1, z); ) {
                if (z < increment && z >= -increment) {
                    z = 0.0D;
                } else if (z > 0.0D) {
                    z -= increment;
                } else {
                    z += increment;
                }
            }
            for (; x != 0.0D && z != 0.0D && isOffsetBBEmpty(x, -1, z); ) {
                if (x < increment && x >= -increment) {
                    x = 0.0D;
                } else if (x > 0.0D) {
                    x -= increment;
                } else {
                    x += increment;
                }
                if (z < increment && z >= -increment) {
                    z = 0.0D;
                } else if (z > 0.0D) {
                    z -= increment;
                } else {
                    z += increment;
                }
            }
        }
        event.setX(x);
        event.setY(y);
        event.setZ(z);
    });

    private boolean isOffsetBBEmpty(double x, double y, double z) {
        return Minecraft.getMinecraft().world.getCollisionBoxes(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getEntityBoundingBox().offset(x, y, z)).isEmpty();
    }
}
