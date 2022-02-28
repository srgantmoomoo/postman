package me.srgantmoomoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.postman.backend.event.EventProcessor;
import me.srgantmoomoo.postman.backend.proxy.CommonProxy;
import me.srgantmoomoo.postman.backend.saveload.ClickGuiLoad;
import me.srgantmoomoo.postman.backend.saveload.ClickGuiSave;
import me.srgantmoomoo.postman.backend.saveload.ConfigStopper;
import me.srgantmoomoo.postman.backend.saveload.SaveLoad;
import me.srgantmoomoo.postman.backend.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.backend.util.Cape;
import me.srgantmoomoo.postman.framework.command.CommandManager;
import me.srgantmoomoo.postman.framework.friend.FriendManager;
import me.srgantmoomoo.postman.framework.module.ModuleManager;
import me.srgantmoomoo.postman.framework.module.setting.SettingManager;
import me.srgantmoomoo.postman.client.clickgui.back.ClickGui;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author SrgantMooMoo
 * @since 11/17/2020
 */

//TODO mob esp crashes.

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	int strong;
	int postman = strong;
	
	public static final Logger log = LogManager.getLogger("postman");
	public static final EventBus EVENT_BUS = new EventManager();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Instance
	public static Main INSTANCE;
	public Main() {
		INSTANCE = this;
	}

	public ModuleManager moduleManager;
	public SettingManager settingManager;
	public CommandManager commandManager;
	public FriendManager friendManager;
	public SaveLoad saveLoad;
	public Cape cape;
	public ClickGui clickGui;
	public EventProcessor eventProcessor;
	public CustomFontRenderer customFontRenderer;
	public ClickGuiSave clickGuiSave;
	public ClickGuiLoad clickGuiLoad;
	
	@EventHandler
	public void init (FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		eventProcessor = new EventProcessor();
		log.info("postman event system initialized.");

		settingManager = new SettingManager();
		log.info("settings system initialized.");

		moduleManager = new ModuleManager();
		log.info("module system initialized.");

		commandManager = new CommandManager();
		log.info("command system initialized.");

		friendManager = new FriendManager();
		log.info("friend system initialized.");

		cape = new Cape();
		log.info("capes initialized.");

		clickGui = new ClickGui();
		log.info("clickGui initialized.");
		
		clickGuiSave = new ClickGuiSave();
		clickGuiLoad = new ClickGuiLoad();
		Runtime.getRuntime().addShutdownHook(new ConfigStopper());
		saveLoad = new SaveLoad();
		log.info("configs initialized.");
		
		log.info("postman initialization finished.");
	}
}