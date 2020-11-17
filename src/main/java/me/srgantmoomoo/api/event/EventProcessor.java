package me.srgantmoomoo.api.event;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventProcessor {

	
	public static EventProcessor INSTANCE;
	Minecraft mc = Minecraft.getMinecraft();

	public EventProcessor(){
		INSTANCE = this;
	}

	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		//Module updates
		// #TO CYBER: DONT DELETE THIS AGAIN BY ACCIDENT DUMBASS
		if (mc.player != null)
			ModuleManager.onUpdate();
	}
	
	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent event) {
		if (event.isCanceled()) return;
		ModuleManager.onWorldRender(event);
	}

	public void init() {
		Main.EVENT_BUS.subscribe(this);
		MinecraftForge.EVENT_BUS.register(this);
	}
}
