package me.srgantmoomoo.postman.client.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.api.util.Reference;
import me.srgantmoomoo.postman.api.util.render.Esp2dHelper;
import me.srgantmoomoo.postman.api.util.render.JTessellator;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.command.Command;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.modules.client.*;
import me.srgantmoomoo.postman.client.module.modules.exploits.*;
import me.srgantmoomoo.postman.client.module.modules.movement.*;
import me.srgantmoomoo.postman.client.module.modules.player.*;
import me.srgantmoomoo.postman.client.module.modules.pvp.*;
import me.srgantmoomoo.postman.client.module.modules.render.*;
import me.srgantmoomoo.postman.client.ui.TabGui;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGuiModule;
import me.srgantmoomoo.postman.client.ui.clickgui.HudEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/*
 * Written by @SrgantMooMoo 11/17/20.
 */

public class ModuleManager {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
		
		//alphabetic
		ModuleManager.modules.add(new AimBot());	
		ModuleManager.modules.add(new AntiHunger());
		ModuleManager.modules.add(new AntiSwing());	
		ModuleManager.modules.add(new Aura());	
		ModuleManager.modules.add(new AutoArmor());	
		ModuleManager.modules.add(new AutoClicker());
		ModuleManager.modules.add(new AutoCopeAndSeethe());
		ModuleManager.modules.add(new AutoCrystal());
		ModuleManager.modules.add(new AutoDisconnect());
		ModuleManager.modules.add(new AutoElytra());
		ModuleManager.modules.add(new AutoGap());	
		ModuleManager.modules.add(new AutoHut());	
		ModuleManager.modules.add(new AutoLog());
		ModuleManager.modules.add(new AutoRespawn());	
		ModuleManager.modules.add(new AutoTotem());	
		ModuleManager.modules.add(new AutoTrap());	
		ModuleManager.modules.add(new AutoWalk());
		ModuleManager.modules.add(new Backdoor2b2t());
		ModuleManager.modules.add(new Blink());	
		ModuleManager.modules.add(new CameraClip());	
		ModuleManager.modules.add(new ChatBot());	
		ModuleManager.modules.add(new ChatSuffix());
		ModuleManager.modules.add(new ChestStealer());
		ModuleManager.modules.add(new CoordExploit());
		ModuleManager.modules.add(new Criticals());	
		ModuleManager.modules.add(new DamageTiltCorrection());	
		ModuleManager.modules.add(new DeathCoords());	
		ModuleManager.modules.add(new Dupe());	
		ModuleManager.modules.add(new ElytraFly());	
		ModuleManager.modules.add(new Esp());
		ModuleManager.modules.add(new FastUse());
		ModuleManager.modules.add(new FootExp());
		ModuleManager.modules.add(new Freecam());	
		ModuleManager.modules.add(new FullBright());	
		ModuleManager.modules.add(new HoleEsp());
		ModuleManager.modules.add(new HoleTp());
		ModuleManager.modules.add(new InventoryMove());	
		ModuleManager.modules.add(new InventorySlots());	
		ModuleManager.modules.add(new Jesus());	
		ModuleManager.modules.add(new LiquidPlace());
		ModuleManager.modules.add(new LogOutSpot());
		ModuleManager.modules.add(new LongJump());
		ModuleManager.modules.add(new LowOffHand());
		ModuleManager.modules.add(new Nametags());	
		ModuleManager.modules.add(new NewChunks());	
		ModuleManager.modules.add(new NoFall());	
		ModuleManager.modules.add(new NoHurtCam());
		ModuleManager.modules.add(new NoPotionEffects());
		ModuleManager.modules.add(new NoPush());
		ModuleManager.modules.add(new NoRain());
		ModuleManager.modules.add(new NoSlow());
		ModuleManager.modules.add(new Peek());
		ModuleManager.modules.add(new PlayerClone());
		ModuleManager.modules.add(new PortalGodMode());
		ModuleManager.modules.add(new ReverseStep());
		ModuleManager.modules.add(new SafeWalk());
		ModuleManager.modules.add(new Scaffold());	
		ModuleManager.modules.add(new SmartOffHand());	
		ModuleManager.modules.add(new Sneak());
		ModuleManager.modules.add(new Speed());
		ModuleManager.modules.add(new Sprint());	
		ModuleManager.modules.add(new Step());
		ModuleManager.modules.add(new Surround());	
		ModuleManager.modules.add(new Timer());	
		ModuleManager.modules.add(new Tracers());
		ModuleManager.modules.add(new Velocity());	
		ModuleManager.modules.add(new ViewModel());	
		ModuleManager.modules.add(new Xray());
		//client
		ModuleManager.modules.add(new Watermark());
		ModuleManager.modules.add(new Totems());
		ModuleManager.modules.add(new Ping());
		ModuleManager.modules.add(new Frames());
		ModuleManager.modules.add(new AutoCInfo());
		ModuleManager.modules.add(new SurroundInfo());
		ModuleManager.modules.add(new ArrayListt());
		ModuleManager.modules.add(new InventoryViewer());
		ModuleManager.modules.add(new Coords());
		ModuleManager.modules.add(new ArmorHud());
		//ModuleManager.modules.add(new KeyStrokes());
		ModuleManager.modules.add(new DiscordRichPresence());
		ModuleManager.modules.add(new ClickGuiModule());
		ModuleManager.modules.add(new HudEditor());
	 	ModuleManager.modules.add(new TabGui());	
	 	ModuleManager.modules.add(new MainMenuInfo());
		ModuleManager.modules.add(new Esp2dHelper());	
		
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public static void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
		Main.getInstance().clickGui.render();
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
	
	public ArrayList<Module> getModuleList() {
		return ModuleManager.modules;
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
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static ArrayList<Module> getModulesInCategory(Category c){
		ArrayList<Module> list = (ArrayList<Module>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
		return list;
	}
	
	public static Module getModuleByName(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m;
	}
}
