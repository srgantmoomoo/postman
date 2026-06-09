package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;

public class Step extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "delay", "vanilla");
    public ModeSetting delayHeight = new ModeSetting("delayHeight", this, "one", "one", "two");
    public NumberSetting vanillaHeight = new NumberSetting("vanillaHeight", this, 2.0, 0.1, 10.0, 0.1);

    private final double[] oneBlockPositions = {0.42, 0.75};
    private final double[] twoBlockPositions = {0.4, 0.75, 0.5, 0.41, 0.83, 1.16, 1.41, 1.57, 1.58, 1.42};

    private int packets;
    private float prevStepHeight;

    public Step() {
        super("step", "steps up blocks lol.", Category.MOVEMENT, 0);
        this.addSettings(mode, delayHeight, vanillaHeight);
    }

    @Override
    public void onEnable() {
        packets = 0;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player != null)
            prevStepHeight = mc.player.getStepHeight();
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player != null)
            mc.player.getAttributeInstance(EntityAttributes.STEP_HEIGHT).setBaseValue(prevStepHeight);
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null || mc.world == null) return;
        if(mc.player.isTouchingWater() || mc.player.isInLava() || mc.player.isClimbing()) return;

        if(mode.is("vanilla")) {
            mc.player.getAttributeInstance(EntityAttributes.STEP_HEIGHT).setBaseValue((float) vanillaHeight.getValue());
            return;
        }

        if(mode.is("normal")) {
            if(!mc.player.horizontalCollision || !mc.player.isOnGround() || mc.player.fallDistance != 0.0f || mc.player.isClimbing())
                return;
            if(mc.options.jumpKey.isPressed())
                return;

            Box box = mc.player.getBoundingBox().expand(0.05).offset(0, 0.05, 0);

            if(!mc.world.isSpaceEmpty(mc.player, box.offset(0, 1.0, 0)))
                return;

            double stepHeight = -1.0;
            for (VoxelShape collision : mc.world.getBlockCollisions(mc.player, box)) {
                Box collisionBox = collision.getBoundingBox();

                if (collisionBox.maxY > stepHeight) {
                    stepHeight = collisionBox.maxY;
                }
            }

            stepHeight -= mc.player.getY();
            if(stepHeight < 0.0 || stepHeight > 1.0) return;

            double x = mc.player.getX();
            double y = mc.player.getY();
            double z = mc.player.getZ();

            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.42, z, true, false));
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + 0.75, z, true, false));
            mc.player.setPosition(x, y + 1.0, z);
            return;
        }

        if(mode.is("delay")) {
            if(mc.player.horizontalCollision && mc.player.isOnGround())
                packets++;

            Box box = mc.player.getBoundingBox().expand(0.05).offset(0, 0.05, 0);

            boolean blockAbove = false;
            int minX = (int) Math.floor(box.minX);
            int maxX = (int) Math.floor(box.maxX + 1.0);
            int minZ = (int) Math.floor(box.minZ);
            int maxZ = (int) Math.floor(box.maxZ + 1.0);
            for(int x = minX; x < maxX; x++) {
                for(int z = minZ; z < maxZ; z++) {
                    if(!mc.world.getBlockState(new net.minecraft.util.math.BlockPos(x, (int) Math.floor(box.maxY + 1), z)).isAir()) {
                        blockAbove = true;
                        break;
                    }
                }
                if(blockAbove) break;
            }
            if(blockAbove) return;

            double[] positions = delayHeight.is("one") ? oneBlockPositions : twoBlockPositions;

            if(mc.player.isOnGround() && !mc.player.isTouchingWater()&& !mc.player.isInLava() && mc.player.horizontalCollision
                    && mc.player.fallDistance == 0 && !mc.options.jumpKey.isPressed() && !mc.player.isClimbing()
                    && packets > positions.length - 2) {

                double x = mc.player.getX();
                double y = mc.player.getY();
                double z = mc.player.getZ();

                for(double pos : positions)
                    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x, y + pos, z, true, false));

                mc.player.setPosition(x, y + positions[positions.length - 1], z);
                packets = 0;
            }
        }
    }
}