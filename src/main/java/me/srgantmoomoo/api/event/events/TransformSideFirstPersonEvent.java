package me.srgantmoomoo.api.event.events;

import me.srgantmoomoo.api.event.Event;
import net.minecraft.util.EnumHandSide;

public class TransformSideFirstPersonEvent extends Event {
	private final EnumHandSide handSide;

	public TransformSideFirstPersonEvent(EnumHandSide handSide){
		this.handSide = handSide;
	}

	public EnumHandSide getHandSide(){
		return handSide;
	}
}
