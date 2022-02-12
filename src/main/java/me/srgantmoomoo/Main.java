//ab08t this 
//loads all needed files and apis 
//dont mess with this unless your adding new apis 

package me.srgantmoomoo;

import me.srgantmoomoo.postman.api.event.EventProcessor;
import me.srgantmoomoo.postman.api.proxy.CommonProxy;
import me.srgantmoomoo.postman.api.save.ClickGuiLoad;
import me.srgantmoomoo.postman.api.save.ClickGuiSave;
import me.srgantmoomoo.postman.api.save.ConfigStopper;
import me.srgantmoomoo.postman.api.save.SaveLoad;
import me.srgantmoomoo.postman.api.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.api.util.render.Cape;
import me.srgantmoomoo.postman.client.command.CommandManager;
import me.srgantmoomoo.postman.client.friend.FriendManager;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.SettingManager;
import me.srgantmoomoo.postman.client.ui.clickgui.back.ClickGui;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

/*
 * Postman Reborn is made by DogeSec and Kyappy
 *              ▄              ▄    
 *             ▌▒█           ▄▀▒▌   
 * 	      ▌▒▒█        ▄▀▒▒▒▐   
 *           ▐▄█▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐   
 *         ▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐   
 *       ▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌   
 *      ▐▒▒▒▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▌  
 *      ▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐  
 *     ▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌ 
 *     ▌░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌ 
 *    ▌▒▒▒▄██▄▒▒▒▒▒▒▒▒░░░░░░░░▒▒▒▐ 
 *    ▐▒▒▐▄█▄█▌▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▒▒▌
 *    ▐▒▒▐▀▐▀▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▐ 
 *     ▌▒▒▀▄▄▄▄▄▄▀▒▒▒▒▒▒▒░▒░▒░▒▒▒▌ 
 *     ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒▒▄▒▒▐  
 *      ▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒▄▒▒▒▒▌  
 *        ▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀   
 *          ▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀     
 *             ▀▀▀▀▀▀▀▀▀▀▀▀        
 * 
 *  /$$      /$$                 /$$                 /$$                      
 * | $$$    /$$$                | $$                | $$                      
 * | $$$$  /$$$$  /$$$$$$   /$$$$$$$  /$$$$$$       | $$$$$$$  /$$   /$$      
 * | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$__  $$      | $$__  $$| $$  | $$      
 * | $$  $$$| $$  /$$$$$$$| $$  | $$| $$$$$$$$      | $$  \ $$| $$  | $$      
 * | $$\  $ | $$ /$$__  $$| $$  | $$| $$_____/      | $$  | $$| $$  | $$      
 * | $$ \/  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$      | $$$$$$$/|  $$$$$$$      
 * |__/     |__/ \_______/ \_______/ \_______/      |_______/  \____  $$      
 *                                                              /$$  | $$      
 *                                                            |  $$$$$$/      
 *                                                             \______/       
 *  /$$$$$$$                                 /$$$$$$                          
 * | $$__  $$                               /$$__  $$                         
 * | $$  \ $$  /$$$$$$   /$$$$$$   /$$$$$$ | $$  \__/  /$$$$$$   /$$$$$$$     
 * | $$  | $$ /$$__  $$ /$$__  $$ /$$__  $$|  $$$$$$  /$$__  $$ /$$_____/     
 * | $$  | $$| $$  \ $$| $$  \ $$| $$$$$$$$ \____  $$| $$$$$$$$| $$           
 * | $$  | $$| $$  | $$| $$  | $$| $$_____/ /$$  \ $$| $$_____/| $$           
 * | $$$$$$$/|  $$$$$$/|  $$$$$$$|  $$$$$$$|  $$$$$$/|  $$$$$$$|  $$$$$$$     
 * |_______/  \______/  \____  $$ \_______/ \______/  \_______/ \_______/     
 *                      /$$  \ $$                                             
 *                     |  $$$$$$/                                             
 *                      \______/                                           
*/

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static ArrayList<Module> modules;
	
	int strong;
	int postman = strong;
	
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static CommandManager commandManager;
	public static FriendManager friendManager;
	public static SaveLoad saveLoad;
	public static Cape cape;
	public static ClickGui clickGui;
	public static EventProcessor eventProcessor;
	public static CustomFontRenderer customFontRenderer;
	public static ClickGuiSave clickGuiSave;
	public static ClickGuiLoad clickGuiLoad;
	
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
	public void init (FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

	log.info("Start debugging api's and event systems here, this is only for the postman mod");

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
		
		log.info("Any issue past this is not a postman issue but there could still be conflicts with other mods, although unlikely");

		log.info("postman loading has finished :: temp added for debug purposes");
	
	}
}
