package me.srgantmoomoo.postman.client.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.api.util.render.Esp2dHelper;
import me.srgantmoomoo.postman.api.util.render.JTessellator;
import me.srgantmoomoo.postman.client.module.modules.bot.*;
import me.srgantmoomoo.postman.client.module.modules.client.*;
import me.srgantmoomoo.postman.client.module.modules.exploits.*;
import me.srgantmoomoo.postman.client.module.modules.hud.*;
import me.srgantmoomoo.postman.client.module.modules.movement.*;
import me.srgantmoomoo.postman.client.module.modules.player.*;
import me.srgantmoomoo.postman.client.module.modules.pvp.*;
import me.srgantmoomoo.postman.client.module.modules.render.*;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGuiModule;
import me.srgantmoomoo.postman.client.ui.clickgui.HudEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/*
 * Written by @SrgantMooMoo 11/17/20.
 */

public class ModuleManager {
	
	public static ArrayList<Module> modules = new ArrayList<>();
	
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
		modules.add(new Blink());	
		modules.add(new CameraClip());	
		modules.add(new ChatSuffix());
		modules.add(new ChestStealer());
		//modules.add(new CoordExploit());
		modules.add(new Criticals());	
		modules.add(new CrystalConfigBot());
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
		//modules.add(new Nametags());	// unstarted           									---------------------------
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
		//modules.add(new Scaffold());	// unstarted
		modules.add(new SmartOffHand());	
		//modules.add(new Sneak());    // unstarted
		modules.add(new Speed());
		modules.add(new Sprint());	
		modules.add(new Step());
		modules.add(new Surround());
		modules.add(new Timer());	
		modules.add(new Tracers());
		modules.add(new Velocity());	
		modules.add(new ViewModel());	
		//modules.add(new Xray());    // unstarted						-------------------------
		//hud
		modules.add(new Watermark());
		modules.add(new World());
		modules.add(new Totems());
		modules.add(new Ping());
		modules.add(new Frames());
		modules.add(new AutoCrystalHud());
		modules.add(new KillAuraHud());
		modules.add(new SurroundHud());
		modules.add(new ArrayListt());
		modules.add(new InventoryViewer());
		modules.add(new PlayerModel());
		modules.add(new Coords());
		modules.add(new ArmorHud());
		modules.add(new HudEditor());
		//client
		modules.add(new ClientFont());
		modules.add(new Capes());
		modules.add(new DiscordRichPresence());
		modules.add(new ClickGuiModule());
	 	//modules.add(new TabGui());
	 	modules.add(new MainMenuWatermark());
		modules.add(new Esp2dHelper());
		modules.add(new GiveMeClout());
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public static void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
		Main.clickGui.render();
	}
	
	public static void onWorldRender(RenderWorldLastEvent event) {
		Minecraft.getMinecraft().profiler.startSection("postman");
		Minecraft.getMinecraft().profiler.startSection("setup");
		JTessellator.prepare();
		RenderEvent e = new RenderEvent(event.getPartialTicks());
		Minecraft.getMinecraft().profiler.endSection();

		modules.stream().filter(module -> module.isToggled()).forEach(module -> {
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
					for(Module m : ModuleManager.modules) {
						if(m.getKey() == keyCode && keyCode > 0) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) { q.printStackTrace(); }
	}
	
	public static void addChatMessage(String message) {
		message = ChatFormatting.AQUA + "@" + ChatFormatting.ITALIC + Reference.NAME + ChatFormatting.GRAY + ": " + message;
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
	
	public static boolean isModuleEnabled(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m.isToggled();
	}
	
	public Module getModule (String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : ModuleManager.modules) {
			if(!m.getName().equals("Esp2dHelper")) {
			if(m.getCategory() == c)
				modules.add(m);
			}
		}
		return modules;
	}
	
	// this works best with panelstudio for whatever reason, ill delete one of these soon.
	public static ArrayList<Module> getModulesInCategory(Category c){
		ArrayList<Module> list = (ArrayList<Module>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
		return list;
	}
	
	public static Module getModuleByName(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m;
	}
}