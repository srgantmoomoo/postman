package me.srgantmoomoo.postman.settings;

import com.lukflug.panelstudio.settings.Toggleable;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class BooleanSetting extends Setting implements Toggleable {
  public boolean enabled;
  
  public BooleanSetting(String name, Module parent, boolean enabled) {
    this.name = name;
    this.parent = parent;
    this.enabled = enabled;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    
    if(Main.saveLoad != null) {
		Main.saveLoad.save();
	}
  }
  
  public void toggle() {
    this.enabled = !this.enabled;
    
    if(Main.saveLoad != null) {
		Main.saveLoad.save();
	}
  }

@Override
public boolean isOn() {
	return this.isEnabled();
}
}
