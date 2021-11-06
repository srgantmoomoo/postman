package me.srgantmoomoo.postman.client.module.modules.player;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Protester extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "clientShitter", "clientShitter", "postmanRespecter", "customMsg");
	public NumberSetting delay = new NumberSetting("delay", this, 20, 0, 100, 1);
	
	public Protester() {
		super("protester", "start your own protest!", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode, delay);
	}
	
	List<String> clients = new ArrayList<>();
	List<String> respects = new ArrayList<>();

    Random random = new Random();
    int tickDelay;
    
    public static String customMsgArg = "";
    public static void setMessage(String msg) {
    	customMsgArg = msg;
    }
    
    @Override
    public void onEnable() {
    	clients.clear();
    	respects.clear();
    	
    	tickDelay = 0;

        clients.add("konas.... really? yikes.");
        clients.add("lol r u really using kami blue??? hahaha wtf! nerd!");
        clients.add("salhack? more like salbad HAHAHAHA.");
        clients.add("impact users deserve ultimate death.");
        clients.add("phobos ca is absolute dog.");
        clients.add("i cant beleive ppl actaully use future. smh.");
        clients.add("rusherhack is a semi decent client tho.");
        clients.add("do ppl actaully still use wurstplustwo? man....");
        clients.add("pyro is just a sal skid made for free money for the devs.");
        clients.add("im sorry but if ur using xulu, get away from me.");
        
        respects.add("average nn client enjoyer: uncool... average postman respecter: very_cool");
        respects.add("wtf is that client???? switch to postman!!!");
        respects.add("mailman client is very good!");
        respects.add("psotman above all!");
        respects.add("i just got new postage from my local post office thanks to postman!");
        respects.add("guys, wtf is this discord.... its... its incredible 0_0 https://discord.gg/Jd8EmEuhb5");
        respects.add("postman on tipity top :)");
        respects.add("postman strong.");
        respects.add("postman stronk?");
        
    }
    
    @Override
    public void onDisable() {
    	clients.clear();
    	respects.clear();
    }
    
    @Override
    public void onUpdate() {
    	if(mode.is("clientShitter")) {
    		tickDelay++;
            if (tickDelay < delay.getValue() * 10) return;
            
            String message = clients.get(random.nextInt(clients.size()));

            mc.player.sendChatMessage(message);
            tickDelay = 0;
    	}
    	if(mode.is("postmanRespecter")) {
            tickDelay++;
            if (tickDelay < delay.getValue() * 10) return;
            
            String message = respects.get(random.nextInt(respects.size()));

            mc.player.sendChatMessage(message);
            
            tickDelay = 0;
    	}
    	if(mode.is("customMsg")) {
    		tickDelay++;
            if (tickDelay < delay.getValue() * 10) return;

            mc.player.sendChatMessage(customMsgArg);
            
            tickDelay = 0;
    	}
    }
    
}
