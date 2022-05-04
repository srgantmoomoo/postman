package me.srgantmoomoo.postman.impl.modules.render;

import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.PacketEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.network.play.server.SPacketTimeUpdate;

public class World extends Module {
	public ModeSetting weather = new ModeSetting("weather", this, "clear", "clear", "rain", "thunderStorm");
	public NumberSetting time = new NumberSetting("time", this, 0.0D, 0.0D, 24000.0D, 1.0D);
	
	public World() {
		super("world", "change world shit.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(weather, time);
	}
	
	@Override
	public void onUpdate() {
		// timeOfDay
		mc.world.setWorldTime((long) time.getValue());
		
		// weather
		if(weather.is("clear")) mc.world.setRainStrength(0);
		if(weather.is("rain")) mc.world.setRainStrength(1);
		if(weather.is("thunderStorm")) mc.world.setRainStrength(2);
	}	
	
	 @EventHandler
	 private final Listener<PacketEvent.Receive> listener = new Listener<>(event -> {
		 if (event.getPacket() instanceof SPacketTimeUpdate) event.cancel();
	 });
}
