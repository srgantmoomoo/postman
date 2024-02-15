package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventCollisionShape;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;

public class Jesus extends Module {
    //public ModeSetting mode = new ModeSetting("mode", this, "solid", "solid", "float");

    //TODO this doesn't work very well. player flies above flowing water!
    public Jesus() {
        super("jesus", "walk on water.", Category.PLAYER, 0);
        //this.addSettings(mode);
    }

    @Override
    public void onEvent(Event e) {
        if(MinecraftClient.getInstance().player == null)
            return;

        if(MinecraftClient.getInstance().player.isSneaking())
            return;

        if(e instanceof EventCollisionShape) {
            if (isBlockFluid(((EventCollisionShape) e).getPos())
                    && !MinecraftClient.getInstance().player.isTouchingWater()
                    && MinecraftClient.getInstance().player.getY() >= ((EventCollisionShape) e).getPos().getY() + 0.9) {
                ((EventCollisionShape) e).setShape(VoxelShapes.fullCube());
            }
        }

        if(e instanceof EventTick) {
            if(MinecraftClient.getInstance().player.isTouchingWater())
                MinecraftClient.getInstance().player.setVelocity(MinecraftClient.getInstance().player.getVelocity().getX(),
                        0.09,
                        MinecraftClient.getInstance().player.getVelocity().getZ());
        }
    }

    private boolean isBlockFluid(BlockPos pos) {
        return MinecraftClient.getInstance().world.getBlockState(pos).getBlock() == Blocks.WATER
                || MinecraftClient.getInstance().world.getBlockState(pos).getBlock() == Blocks.LAVA;
    }

}
