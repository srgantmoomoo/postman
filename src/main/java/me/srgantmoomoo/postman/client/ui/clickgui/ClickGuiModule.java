package me.srgantmoomoo.postman.client.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;

public class ClickGuiModule extends Module {
	public static ClickGuiModule INSTANCE;
	
	public ModeSetting theme = new ModeSetting("theme", this, "new", "new", "old");
	public NumberSetting animationSpeed = new NumberSetting("animation", this, 150, 0, 1000, 50);
	public NumberSetting scrolls = new NumberSetting("scrollSpeed", this, 10, 0, 100, 1);
	public ModeSetting scrollMode = new ModeSetting("scroll", this, "container", "container", "screen");
	public ColorSetting enabledColor = new ColorSetting("enabledColor", this, new JColor(103, 167, 221, 255)); //(0, 157, 255, 255));
	public ColorSetting backgroundColor = new ColorSetting("bgColor", this, new JColor(0, 0, 0, 255)); //(0, 121, 194, 255));
	public ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", this, new JColor(0, 0, 0, 255));
	public ColorSetting outlineColor = new ColorSetting("settingsOutline", this, new JColor(255, 255, 255, 255));
	public ColorSetting fontColor = new ColorSetting("categoryColor", this, new JColor(121, 193, 255, 255)); 
	public NumberSetting opacity = new NumberSetting("opacity", this, 255, 0, 255, 5);
	
public ClickGuiModule() {
	super("clickGuiModule", "classic hud", Keyboard.KEY_RSHIFT, Category.CLIENT);
	this.addSettings(theme,scrollMode,scrolls,animationSpeed,opacity,fontColor,enabledColor,backgroundColor,settingBackgroundColor,outlineColor);
	INSTANCE = this;
}

public static Module getClickGuiModule() {
	return INSTANCE;
}

	public void onEnable() {
		Main.getInstance().clickGui.enterGUI();
	}

	public void onUpdate() {

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			this.setToggled(!toggled);
		}
		
		if(ModuleManager.getModuleByName("hudEditor").isToggled()) {
			this.setToggled(!toggled);
		}
		
	}

	public void onDisable() {
		}
}