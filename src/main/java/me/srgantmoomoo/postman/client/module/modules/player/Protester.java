package me.srgantmoomoo.postman.client.module.modules.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;

public class Protester extends Module {
	public BooleanSetting shitOnClients = new BooleanSetting("shitOnClients", this, true);
	public BooleanSetting postmanRespecter = new BooleanSetting("postmanRespecter", this, true);
	public NumberSetting delay = new NumberSetting("delay", this, 20, 0, 100, 1);
	
	public Protester() {
		super("protester", "protests about postmans greatness.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(shitOnClients, postmanRespecter, delay);
	}
	
	List<String> clients = new ArrayList<>();
	List<String> respects = new ArrayList<>();

    Random random = new Random();
    int tickDelay;
    
    public void onEnable() {
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
    }
    
    public void onDisable() {
    	clients.clear();
    }
    
    @Override
    public void onUpdate() {
    	if(shitOnClients.isEnabled()) {
    		tickDelay++;
            if (tickDelay < delay.getValue() * 10) return;
            
            String s = clients.get(random.nextInt(clients.size()));

            mc.player.sendChatMessage(s);
            tickDelay = 0;
    	}
    	if(postmanRespecter.isEnabled()) {
            tickDelay++;
            if (tickDelay < delay.getValue() * 10) return;
            
            String message = respects.get(random.nextInt(respects.size()));

            mc.player.sendChatMessage(message);
            tickDelay = 0;
    	}
    }
    
}
