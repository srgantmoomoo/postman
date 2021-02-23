package me.srgantmoomoo;

import java.awt.Font;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.postman.api.event.EventProcessor;
import me.srgantmoomoo.postman.api.proxy.CommonProxy;
import me.srgantmoomoo.postman.api.save.ClickGuiLoad;
import me.srgantmoomoo.postman.api.save.ClickGuiSave;
import me.srgantmoomoo.postman.api.save.ConfigStopper;
import me.srgantmoomoo.postman.api.save.SaveLoad;
import me.srgantmoomoo.postman.api.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.notification.Notification;
import me.srgantmoomoo.postman.client.notification.NotificationType;
import me.srgantmoomoo.postman.client.setting.SettingManager;
import me.srgantmoomoo.postman.client.ui.TabGui;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGui;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static ArrayList<Module> modules;
	
	int strong;
	int postman = strong;
	
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static CommandManager commandManager;
	public static SaveLoad saveLoad;
	public ClickGui clickGui;
	public static TabGui tabGui;
	public EventProcessor eventProcessor;
	public static Notification notification;
	public CustomFontRenderer customFontRenderer;
	public ClickGuiSave clickGuiSave;
	public ClickGuiLoad clickGuiLoad;
	
	public static final Logger log = LogManager.getLogger("postman");
	
	public static final EventBus EVENT_BUS = new EventManager();
	
	@Instance
	public static Main instance;
	
	public Main() {
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init (FMLInitializationEvent event) {
		eventProcessor = new EventProcessor();
		eventProcessor.init();
		log.info("postman event system initialized.");
		
		MinecraftForge.EVENT_BUS.register(this);
		log.info("forge event system initialized.");
		
		customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), false,false);
		log.info("custom font initialized.");
		
		settingManager = new SettingManager();
		log.info("settings system initialized.");
		
		MinecraftForge.EVENT_BUS.register(new ModuleManager());
		// ^^^ module manager needs to register to minecraft forge event for things like onkeypressed
		moduleManager = new ModuleManager();
		log.info("module system initialized.");
		
		commandManager = new CommandManager();
		log.info("command system initialized.");
		
		MinecraftForge.EVENT_BUS.register(new TabGui());
		tabGui = new TabGui();
		log.info("tabgui initialized.");
		
		clickGui = new ClickGui();
		log.info("clickGui initialized.");
		
		clickGuiSave = new ClickGuiSave();
		clickGuiLoad = new ClickGuiLoad();
		Runtime.getRuntime().addShutdownHook(new ConfigStopper());
		saveLoad = new SaveLoad();
		log.info("configs initialized.");
		
		log.info("postman initialization finished.");
	
	} //pp
	
	@EventHandler
	public void postInit (FMLPostInitializationEvent event) {
		
	}
}
