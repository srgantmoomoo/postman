package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.RenderRainEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class World extends Module {
	public BooleanSetting noWeather = new BooleanSetting("noWeather", this, true);
	public ModeSetting weather = new ModeSetting("weather", this, "snow", "snow", "rain", "thunderStorm", "clear");
	public ModeSetting timeOfDay = new ModeSetting("timeOfDay", this, "sunrise", "sunrise", "midday", "sunset", "midday");
	public NumberSetting time = new NumberSetting("time", this, 0.0D, 0.0D, 24000.0D, 1.0D);
	
	public World() {
		super("world", "change world shit.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(noWeather, weather, timeOfDay, time);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}

	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	// noWeather
	@EventHandler
	private Listener<RenderRainEvent> onRain = new Listener<>(event -> {
		if(noWeather.isEnabled()) {
		    if (mc.world == null)
		        return;
		    event.cancel();
		}
	});
	
	// weather
	
	
	// timeOfDay
	public void onUpdate() {
		mc.world.setWorldTime((long) time.getValue());
	}	
}
