package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    public NumberSetting vanillaSpeed = new NumberSetting("vanillaSpeed", this, 1.0, 0.1, 4.0, 0.1);
    public NumberSetting strafeSpeed  = new NumberSetting("strafeSpeed",  this, 1.9, 0.0, 4.0, 0.1);
    public ModeSetting mode = new ModeSetting("mode", this, "strafe", "strafe", "vanilla");

    private double playerSpeed = 0;
    private boolean slowDown = false;
    private long lastJump = 0;

    public Speed() {
        super("speed", "speeeeeeeeddddyyy.", Category.MOVEMENT, 0);
        this.addSettings(mode, vanillaSpeed, strafeSpeed);
    }

    @Override
    public void onEnable() {
        playerSpeed = getBaseMoveSpeed();
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null)
            mc.player.setVelocity(0, mc.player.getVelocity().y, 0);
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) { disable(); return; }
        if (mc.player.isInLava() || mc.player.isTouchingWater() || mc.player.isClimbing()) return;

        Vec3d vel = mc.player.getVelocity();
        float yaw  = (float) Math.toRadians(mc.player.getYaw());

        boolean forward = mc.options.forwardKey.isPressed();
        boolean back = mc.options.backKey.isPressed();
        boolean left = mc.options.leftKey.isPressed();
        boolean right = mc.options.rightKey.isPressed();

        boolean moving = forward || back || left || right;

        if (mode.is("vanilla")) {
            if (moving) {
                double spd = vanillaSpeed.getValue() * 0.2;
                double motX = 0, motZ = 0;
                if (forward) {
                    motX -= Math.sin(yaw) * spd; motZ += Math.cos(yaw) * spd;
                }
                if (back) {
                    motX += Math.sin(yaw) * spd; motZ -= Math.cos(yaw) * spd;
                }
                if (left) {
                    motX += Math.cos(yaw) * spd; motZ += Math.sin(yaw) * spd;
                }
                if (right) {
                    motX -= Math.cos(yaw) * spd; motZ -= Math.sin(yaw) * spd;
                }
                mc.player.setVelocity(motX, vel.y, motZ);
            }
            return;
        }

        if(mode.is("strafe")) {
            long now = System.currentTimeMillis();

            if(mc.player.isOnGround() && moving && (now - lastJump) > 400) {
                playerSpeed = getBaseMoveSpeed() * strafeSpeed.getValue();
                slowDown = true;
                lastJump = now;
                mc.player.setVelocity(mc.player.getVelocity().x, 0.42, mc.player.getVelocity().z);
            }

            if(!mc.player.isOnGround()) {
                if(slowDown) {
                    playerSpeed -= playerSpeed * 0.04;
                } else {
                    playerSpeed -= playerSpeed / 159.0;
                }
                playerSpeed = Math.max(playerSpeed, getBaseMoveSpeed());

                if(moving) {
                    double[] dir = getDirection(mc, playerSpeed);
                    mc.player.setVelocity(dir[0], mc.player.getVelocity().y, dir[1]);
                }
            } else {
                if(slowDown || mc.player.horizontalCollision) {
                    playerSpeed = getBaseMoveSpeed();
                    slowDown = false;
                }
            }
        }
    }

    private double[] getDirection(MinecraftClient mc, double speed) {
        float yaw = (float) Math.toRadians(mc.player.getYaw());
        float forward = mc.player.input.movementForward;
        float strafe = -mc.player.input.movementSideways;

        double len = Math.sqrt(forward * forward + strafe * strafe);
        if(len > 1.0) {
            forward /= len;
            strafe /= len;
        }

        double motX = (-Math.sin(yaw) * forward - Math.cos(yaw) * strafe) * speed;
        double motZ = (Math.cos(yaw) * forward - Math.sin(yaw) * strafe) * speed;
        return new double[]{motX, motZ};
    }

    private double getBaseMoveSpeed() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return 0.2;
        return mc.player.getMovementSpeed() * 2.0;
    }
}
