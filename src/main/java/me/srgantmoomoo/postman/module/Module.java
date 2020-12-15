package me.srgantmoomoo.postman.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.srgantmoomoo.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.settings.KeybindSetting;
import me.srgantmoomoo.postman.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public abstract class Module {
	
	protected static final Minecraft mc = Minecraft.getMinecraft();
	public static ArrayList<Module> modules;
	
	public String name, description;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	public boolean toggled;
	public boolean expanded;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, String description, int key, Category category) {
		super();
		this.name = name;
		this.description = description;
		keyCode.code = key;
		this.addSettings(keyCode);
		this.category = category;
		this.toggled = false;
	}
	
	public void onWorldRender(RenderEvent event) {}
	
	public void onUpdate(){}
	
	public void onRender(){}
	
	protected void enable(){}

	protected void disable(){}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getKey() {
		return keyCode.code;
	}
	
	public void setKey(int key) {
		this.keyCode.code = key;
		
		 if(Main.saveLoad != null) {
				Main.saveLoad.save();
			}
	} 
	
	public boolean isToggled() {
		return toggled;
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		if(this.toggled) {
			this.onEnable();
		}else {
			this.onDisable();
		}
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public void toggle() {
		this.toggled = !this.toggled;
		
		if(this.toggled) {
			this.onEnable();
		}else {
			this.onDisable();
		}
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
		Main.EVENT_BUS.subscribe(this);
		enable();
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.register(this);
		Main.EVENT_BUS.subscribe(this);
		disable();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Category getCateogory() {
		return this.category;
	}
	
}
