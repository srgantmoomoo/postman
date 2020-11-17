package me.srgantmoomoo.postman.settings;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class BooleanSetting extends Setting {
  public boolean enabled;
  
  public BooleanSetting(String name, boolean enabled) {
    this.name = name;
    this.enabled = enabled;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
  public void toggle() {
    this.enabled = !this.enabled;
  }
}
