package me.srgantmoomoo.postman.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.srgantmoomoo.api.event.events.RenderEvent;
import me.srgantmoomoo.api.util.render.Esp2dHelper;
import me.srgantmoomoo.api.util.render.JTessellator;
import me.srgantmoomoo.postman.module.modules.client.ArmorHud;
import me.srgantmoomoo.postman.module.modules.client.ArrayListt;
import me.srgantmoomoo.postman.module.modules.client.DiscordRichPresence;
import me.srgantmoomoo.postman.module.modules.client.Hey;
import me.srgantmoomoo.postman.module.modules.client.Info;
import me.srgantmoomoo.postman.module.modules.client.InventoryViewer;
import me.srgantmoomoo.postman.module.modules.client.KeyStrokes;
import me.srgantmoomoo.postman.module.modules.client.MainMenuInfo;
import me.srgantmoomoo.postman.module.modules.client.Watermark;
import me.srgantmoomoo.postman.module.modules.exploits.AntiHunger;
import me.srgantmoomoo.postman.module.modules.exploits.Backdoor2b2t;
import me.srgantmoomoo.postman.module.modules.exploits.CoordExploit;
import me.srgantmoomoo.postman.module.modules.exploits.Dupe;
import me.srgantmoomoo.postman.module.modules.exploits.ElytraFly;
import me.srgantmoomoo.postman.module.modules.exploits.PlayerClone;
import me.srgantmoomoo.postman.module.modules.player.AutoArmor;
import me.srgantmoomoo.postman.module.modules.player.AutoTotem;
import me.srgantmoomoo.postman.module.modules.player.ChatBot;
import me.srgantmoomoo.postman.module.modules.player.ChatWatermark;
import me.srgantmoomoo.postman.module.modules.player.ChestStealer;
import me.srgantmoomoo.postman.module.modules.player.InventoryMove;
import me.srgantmoomoo.postman.module.modules.player.Jesus;
import me.srgantmoomoo.postman.module.modules.player.NoPush;
//import me.srgantmoomoo.postman.module.modules.player.NoSlow;
import me.srgantmoomoo.postman.module.modules.player.Scaffold;
import me.srgantmoomoo.postman.module.modules.player.Sprint;
import me.srgantmoomoo.postman.module.modules.player.Velocity;
import me.srgantmoomoo.postman.module.modules.pvp.AimBot;
import me.srgantmoomoo.postman.module.modules.pvp.Aura;
import me.srgantmoomoo.postman.module.modules.pvp.AutoClicker;
import me.srgantmoomoo.postman.module.modules.pvp.AutoCrystal;
import me.srgantmoomoo.postman.module.modules.pvp.AutoLog;
import me.srgantmoomoo.postman.module.modules.pvp.FastUse;
import me.srgantmoomoo.postman.module.modules.pvp.HoleTp;
import me.srgantmoomoo.postman.module.modules.pvp.LogOutSpot;
import me.srgantmoomoo.postman.module.modules.pvp.Surround;
import me.srgantmoomoo.postman.module.modules.render.Esp;
import me.srgantmoomoo.postman.module.modules.render.Freecam;
import me.srgantmoomoo.postman.module.modules.render.FullBright;
import me.srgantmoomoo.postman.module.modules.render.HoleEsp;
import me.srgantmoomoo.postman.module.modules.render.LowOffHand;
import me.srgantmoomoo.postman.module.modules.render.Nametags;
import me.srgantmoomoo.postman.module.modules.render.NewChunks;
import me.srgantmoomoo.postman.module.modules.render.NoHurtCam;
import me.srgantmoomoo.postman.module.modules.render.Peek;
import me.srgantmoomoo.postman.module.modules.render.Tracers;
import me.srgantmoomoo.postman.module.modules.render.ViewModel;
import me.srgantmoomoo.postman.module.modules.render.Weather;
import me.srgantmoomoo.postman.module.modules.render.Xray;
import me.srgantmoomoo.postman.ui.TabGui;
import me.srgantmoomoo.postman.ui.clickgui.ClickGuiModule;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/*
 * Written by @SrgantMooMoo 11/17/20.
 */

public class ModuleManager {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
		/*
		
		//exploits
		this.modules.add(new Dupe());
		this.modules.add(new ElytraFly());
		this.modules.add(new AntiHunger());
		this.modules.add(new Backdoor2b2t());
		//render
		this.modules.add(new Freecam());
		this.modules.add(new FullBright());
		this.modules.add(new Nametags());
		this.modules.add(new NewChunks());
		this.modules.add(new Peek());
		this.modules.add(new Weather());
		this.modules.add(new Xray());
		this.modules.add(new ItemEsp());
		this.modules.add(new PlayerEsp());
		this.modules.add(new StorageEsp());
		this.modules.add(new PlayerTracers());
		this.modules.add(new StorageTracers());
		//player
		this.modules.add(new AutoTotem());
		this.modules.add(new ChatBot());
		this.modules.add(new InvWalk());
		this.modules.add(new Jesus());
		this.modules.add(new NoPush());
		this.modules.add(new Scaffold());
		this.modules.add(new Sprint());
		this.modules.add(new Timer());
		this.modules.add(new Velocity());
		//pvp
		this.modules.add(new AutoLog());
		this.modules.add(new HoleEsp());
		this.modules.add(new AimBot());
		this.modules.add(new Aura());
		this.modules.add(new AutoCrystal());
		this.modules.add(new BowSpam());
		this.modules.add(new Surround());
		this.modules.add(new LogOutSpot());
		//client
		this.modules.add(new TabGui());
		this.modules.add(new DiscordRichPresence());
		//hud
		this.modules.add(new DarkHud());
		this.modules.add(new LightHud());
		
		*/
		
		//alphabetic
		ModuleManager.modules.add(new AimBot());	
		ModuleManager.modules.add(new AntiHunger());	
		ModuleManager.modules.add(new Aura());	
		ModuleManager.modules.add(new AutoArmor());	
		ModuleManager.modules.add(new AutoClicker());
		ModuleManager.modules.add(new AutoCrystal());	
		ModuleManager.modules.add(new AutoLog());	
		ModuleManager.modules.add(new AutoTotem());	
		ModuleManager.modules.add(new Backdoor2b2t());
		ModuleManager.modules.add(new ChatBot());	
		ModuleManager.modules.add(new ChatWatermark());
		ModuleManager.modules.add(new ChestStealer());
		ModuleManager.modules.add(new CoordExploit());
		ModuleManager.modules.add(new Dupe());	
		ModuleManager.modules.add(new ElytraFly());	
		ModuleManager.modules.add(new Esp());
		ModuleManager.modules.add(new FastUse());
		ModuleManager.modules.add(new Freecam());	
		ModuleManager.modules.add(new FullBright());	
		ModuleManager.modules.add(new HoleEsp());
		ModuleManager.modules.add(new HoleTp());
		ModuleManager.modules.add(new InventoryMove());	
		ModuleManager.modules.add(new Jesus());	
		ModuleManager.modules.add(new LogOutSpot());
		ModuleManager.modules.add(new LowOffHand());
		ModuleManager.modules.add(new Nametags());	
		ModuleManager.modules.add(new NewChunks());	
		ModuleManager.modules.add(new NoPush());
		ModuleManager.modules.add(new NoHurtCam());
		//ModuleManager.modules.add(new NoSlow());
		ModuleManager.modules.add(new Peek());
		ModuleManager.modules.add(new PlayerClone());	
		ModuleManager.modules.add(new Scaffold());	
		ModuleManager.modules.add(new Sprint());		
		ModuleManager.modules.add(new Surround());	
		ModuleManager.modules.add(new Tracers());
		ModuleManager.modules.add(new Velocity());	
		ModuleManager.modules.add(new ViewModel());	
		ModuleManager.modules.add(new Weather());	
		ModuleManager.modules.add(new Xray());
		//client
		ModuleManager.modules.add(new Watermark());
		ModuleManager.modules.add(new ArrayListt());
		ModuleManager.modules.add(new Info());
		ModuleManager.modules.add(new InventoryViewer());
		ModuleManager.modules.add(new Hey());
		ModuleManager.modules.add(new ArmorHud());
		ModuleManager.modules.add(new KeyStrokes());
		//ModuleManager.modules.add(new ClassicHud());
		//ModuleManager.modules.add(new LightHud());	
		//ModuleManager.modules.add(new DarkHud());
		ModuleManager.modules.add(new DiscordRichPresence());
		ModuleManager.modules.add(new ClickGuiModule());
	 	ModuleManager.modules.add(new TabGui());	
	 	ModuleManager.modules.add(new MainMenuInfo());
		ModuleManager.modules.add(new Esp2dHelper());	
		
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public static void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
	}
	
	public static void onWorldRender(RenderWorldLastEvent event) {
		Minecraft.getMinecraft().profiler.startSection("gamesense");
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
		ArrayList<Module> list = (ArrayList<Module>) modules.stream().filter(m -> m.category.equals(c)).collect(Collectors.toList());
		return list;
	}
	
	public static Module getModuleByName(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m;
	}
}
