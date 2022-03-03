package me.srgantmoomoo.postman.framework.module;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.backend.util.render.JTessellator;
import me.srgantmoomoo.postman.impl.modules.bot.*;
import me.srgantmoomoo.postman.impl.modules.client.*;
import me.srgantmoomoo.postman.impl.modules.exploits.*;
import me.srgantmoomoo.postman.impl.modules.hud.*;
import me.srgantmoomoo.postman.impl.modules.movement.*;
import me.srgantmoomoo.postman.impl.modules.player.*;
import me.srgantmoomoo.postman.impl.modules.pvp.*;
import me.srgantmoomoo.postman.impl.modules.render.*;
import me.srgantmoomoo.postman.impl.clickgui.front.ClickGuiModule;
import me.srgantmoomoo.postman.impl.clickgui.front.HudEditor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/*
 * Written by @SrgantMooMoo 11/17/20.
 */

public class ModuleManager {
	public ArrayList<Module> modules = new ArrayList<>();
	
	public ModuleManager() { 
		MinecraftForge.EVENT_BUS.register(this);
		
		//alphabetic
		//modules.add(new AimBot());	// unstarted
		modules.add(new AntiHunger());
		//modules.add(new AntiNick());	// w i p
		modules.add(new AntiSwing());
		modules.add(new AutoArmor());	
		modules.add(new AutoClicker());
		modules.add(new AutoCope());
		modules.add(new AutoCrystal());
		modules.add(new AutoDisconnect());
		modules.add(new AutoGap());	
		//modules.add(new AutoHut());   // unstarted
		modules.add(new AutoMine());	
		modules.add(new AutoReconnect());	
		modules.add(new AutoRespawn());	
		modules.add(new AutoTotem());	
		//modules.add(new AutoTrap());   // unstarted
		modules.add(new AutoUse());	
		modules.add(new AutoWalk());
		modules.add(new Backdoor2b2t());
		modules.add(new Baritone());                       // baritone
		modules.add(new Blink());	
		modules.add(new CameraClip());	
		modules.add(new ChatSuffix());
		modules.add(new ChestStealer());
		modules.add(new ConfigCrystal());
		//modules.add(new CoordExploit());
		modules.add(new Criticals());	
		modules.add(new DamageTiltCorrection());	
		modules.add(new DeathCoords());	                             // --- integrate with notifications.
		modules.add(new Dupe());	
		//modules.add(new ElytraFly());   // unstarted
		//modules.add(new ElytraReplace());  // unstarted
		modules.add(new Esp());
		modules.add(new FastUse());
		modules.add(new Fly());
		modules.add(new FootExp());
		modules.add(new Freecam());	
		modules.add(new FullBright());	
		modules.add(new GuiMove());
		modules.add(new HoleEsp());
		modules.add(new HoleTp());
		modules.add(new InventoryPlus());	
		modules.add(new Jesus());	
		modules.add(new KillAura());	
		modules.add(new LiquidPlace());
		//modules.add(new LogOutSpot());  // unstarted
		//modules.add(new LongJump());   // unstarted
		modules.add(new LowOffHand());
		modules.add(new Mcf());
		modules.add(new Multitask());
		modules.add(new Nametags());	// unstarted           									---------------------------
		//modules.add(new NewChunks());	// unstarted
		modules.add(new NoFall());
		modules.add(new NoHandShake());	
		modules.add(new NoPush());
		modules.add(new NoRender());
		modules.add(new NoSlow());
		modules.add(new OffHandBot());
		modules.add(new PacketCancellor());
		modules.add(new Peek());
		modules.add(new PlayerClone());
		modules.add(new PortalGodMode());
		modules.add(new Protester());
		modules.add(new Refill());
		modules.add(new ReverseStep());
		modules.add(new SafeWalk());
		modules.add(new SelfFill());
		//modules.add(new Scaffold());	// unstarted
		modules.add(new SmartOffHand());	
		modules.add(new Sneak());
		modules.add(new Speed());
		modules.add(new Sprint());	
		modules.add(new Step());
		modules.add(new Surround());
		modules.add(new Timer());	
		modules.add(new Tracers());
		modules.add(new Velocity());	
		modules.add(new ViewModel());	
		modules.add(new World());
		//modules.add(new Xray());    // unstarted						-------------------------
		//hud
		modules.add(new Watermark());
		modules.add(new Welcomer());
		modules.add(new Totems());
		modules.add(new Gapples());
		modules.add(new Crystals());
		modules.add(new Ping());
		modules.add(new Frames());
		modules.add(new AutoCrystalHud());
		modules.add(new KillAuraHud());
		modules.add(new SurroundHud());
		modules.add(new ArrayListt());
		modules.add(new InventoryViewer());
		modules.add(new PlayerModel());
		modules.add(new TargetHud());
		modules.add(new Coords());
		modules.add(new NetherCoords());
		modules.add(new ArmorHud());
		modules.add(new HudEditor());
		//client
		modules.add(new ClientFont());
		modules.add(new Capes());
		modules.add(new DiscordRichPresence());
		modules.add(new ClickGuiModule());
	 	//modules.add(new TabGui());
	 	modules.add(new MainMenuWatermark());
	}
	
	public void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
		Main.INSTANCE.clickGui.render();
	}
	
	public void onWorldRender(RenderWorldLastEvent event) {
		Minecraft.getMinecraft().profiler.startSection("postman");
		Minecraft.getMinecraft().profiler.startSection("setup");
		JTessellator.prepare();
		RenderEvent e = new RenderEvent(event.getPartialTicks());
		Minecraft.getMinecraft().profiler.endSection();

		modules.stream().filter(Module::isToggled).forEach(module -> {
			Minecraft.getMinecraft().profiler.startSection(module.getName());
			module.onWorldRender(e);
			Minecraft.getMinecraft().profiler.endSection();
		});

		Minecraft.getMinecraft().profiler.startSection("release");
		JTessellator.release();
		Minecraft.getMinecraft().profiler.endSection();
		Minecraft.getMinecraft().profiler.endSection();
	}
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
			return;
		try {
			if(Keyboard.isCreated()) {
				if(Keyboard.getEventKeyState()) {
					int keyCode = Keyboard.getEventKey();
					if(keyCode <= 0)
						return;
					for(Module m : modules) {
						if(m.getKey() == keyCode && keyCode > 0) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) { q.printStackTrace(); }
	}
	
	public boolean isModuleEnabled(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m.isToggled();
	}
	
	public Module getModule (String name) {
		for (Module m : modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public ArrayList<Module> getModules() {
		return modules;
	}

	public ArrayList<Module> getModulesInCategory(Category c){
		return (ArrayList<Module>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
	}
	
	public Module getModuleByName(String name){
		return modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
}