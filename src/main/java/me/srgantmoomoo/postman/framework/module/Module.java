package me.srgantmoomoo.postman.framework.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.lukflug.panelstudio.settings.Toggleable;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleDisableEvent;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleEnableEvent;
import me.srgantmoomoo.postman.framework.module.setting.Setting;
import me.srgantmoomoo.postman.framework.module.setting.settings.KeybindSetting;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

/**
 * @author SrgantMooMoo
 * @since 4/16/22
 */

public abstract class Module implements Toggleable {
	protected static final Minecraft mc = Minecraft.getMinecraft();
	
	public final String name, description;
	public final KeybindSetting keyCode = new KeybindSetting(0);
	public final Category category;
	protected boolean toggled;
	private int index;
	public List<Setting> settings = new ArrayList<>();
	
	public Module(String name, String description, int key, Category category) {
		this.name = name;
		this.description = description;
		this.keyCode.setKey(key);
		this.addSettings(keyCode);
		this.category = category;
		this.toggled = false;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(setting -> setting == this.keyCode ? 1 : 0));
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getKey() {
		return this.keyCode.getKey();
	}
	
	public void setKey(int key) {
		this.keyCode.setKey(key);
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	} 
	
	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}

	@Override
	public final boolean isOn() {
		return this.toggled;
	}

	@Override
	public void toggle() {
		if(!toggled) {
			enable();
		} else {
			disable();
		}
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}
	
	public boolean isToggled() {
		return this.toggled;
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		if(toggled) {
			Main.EVENT_BUS.subscribe(this);
		} else {
			Main.EVENT_BUS.unsubscribe(this);
		}
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}
	
	protected void enable() {
		this.onEnable();
		this.setToggled(true);
		Main.EVENT_BUS.post(new PostmanModuleEnableEvent(this));
	}
	
	protected void disable() {
		this.onDisable();
		this.setToggled(false);
		Main.EVENT_BUS.post(new PostmanModuleDisableEvent(this));
	}
	
	protected void onEnable() {}
	
	protected void onDisable() {}
	
	public void onWorldRender(RenderEvent event) {}
	
	public void onUpdate() {}
	
	public void onRender() {}
}
