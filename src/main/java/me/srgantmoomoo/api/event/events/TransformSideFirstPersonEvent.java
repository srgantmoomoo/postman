package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.GameSenseEvent;
import net.minecraft.util.EnumHandSide;

public class TransformSideFirstPersonEvent extends GameSenseEvent {
	private final EnumHandSide handSide;

	public TransformSideFirstPersonEvent(EnumHandSide handSide){
		this.handSide = handSide;
	}

	public EnumHandSide getHandSide(){
		return handSide;
	}
}
