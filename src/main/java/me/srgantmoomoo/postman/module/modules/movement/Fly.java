package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Fly extends Module {
    public NumberSetting speed = new NumberSetting("speed", this, 10, 1, 20, 1);
    public ModeSetting mode = new ModeSetting("mode", this, "vanilla", "vanilla", "packet");

    public Fly() {
        super("fly", "its a bird, its a plane!", Category.MOVEMENT, 0);
        this.addSettings(speed, mode);
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        if (mode.is("vanilla")) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().allowFlying = true;
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        if (mode.is("vanilla")) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().setFlySpeed(0.05f);
            if (!mc.player.getAbilities().creativeMode)
                mc.player.getAbilities().allowFlying = false;
        }
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (mode.is("vanilla")) {
            mc.player.getAbilities().setFlySpeed((float) (speed.getValue() / 100f));
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().allowFlying = true;
        }

        if (mode.is("packet")) {
            double spd = speed.getValue() * 0.02;
            float yaw = (float) Math.toRadians(mc.player.getYaw());

            double motionX = 0, motionZ = 0, motionY = 0;

            boolean forward = mc.options.forwardKey.isPressed();
            boolean back = mc.options.backKey.isPressed();
            boolean left = mc.options.leftKey.isPressed();
            boolean right = mc.options.rightKey.isPressed();
            boolean jump = mc.options.jumpKey.isPressed();
            boolean sneak = mc.options.sneakKey.isPressed();

            if (forward) {
                motionX -= Math.sin(yaw) * spd; motionZ += Math.cos(yaw) * spd;
            }
            if (back) {
                motionX += Math.sin(yaw) * spd; motionZ -= Math.cos(yaw) * spd;
            }
            if (left) {
                motionX += Math.cos(yaw) * spd; motionZ += Math.sin(yaw) * spd;
            }
            if (right) {
                motionX -= Math.cos(yaw) * spd; motionZ -= Math.sin(yaw) * spd;
            }
            if (jump) motionY = spd;
            if (sneak) motionY = -spd;

            double newX = mc.player.getX() + motionX;
            double newY = mc.player.getY() + motionY;
            double newZ = mc.player.getZ() + motionZ;

            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(newX, newY, newZ, false, false));
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(newX, newY - 42069, newZ, true, false));

            mc.player.setPosition(newX, newY, newZ);
            mc.player.setVelocity(0, 0, 0);
        }
    }
}
