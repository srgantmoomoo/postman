package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.Event;
import net.minecraft.util.math.BlockPos;

public class DestroyBlockEvent extends Event {

	BlockPos blockPos;

	public DestroyBlockEvent(BlockPos blockPos) {
		super();
		this.blockPos = blockPos;
	}

	public BlockPos getBlockPos() {
		return this.blockPos;
	}

	public void setBlockPos(BlockPos blockPos) {
		this.blockPos = blockPos;
	}
}