package me.srgantmoomoo.postman.event.events;

import me.srgantmoomoo.postman.event.Event;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;

public class EventCollisionShape extends Event<EventCollisionShape> {

    private BlockState state;
    private BlockPos pos;
    private VoxelShape shape;

    public EventCollisionShape(BlockState state, BlockPos pos, VoxelShape shape) {
        this.state = state;
        this.pos = pos;
        this.shape = shape;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public VoxelShape getShape() {
        return shape;
    }

    public void setShape(VoxelShape shape) {
        this.shape = shape;
    }
}
