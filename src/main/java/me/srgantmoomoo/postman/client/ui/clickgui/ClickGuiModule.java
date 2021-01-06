package me.srgantmoomoo.postman.client.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.util.ResourceLocation;

public class ClickGuiModule extends Module {
	private static Module ClickGuiModule;
	public ClickGuiModule INSTANCE;
	
	public static NumberSetting animationSpeed = new NumberSetting("animation", ClickGuiModule, 150, 0, 1000, 50);
	public static NumberSetting scrolls = new NumberSetting("scrollSpeed", ClickGuiModule, 10, 0, 100, 1);
	public static ModeSetting scrollMode = new ModeSetting("scroll", ClickGuiModule, "container", "container", "screen");
	public static ColorSetting enabledColor = new ColorSetting("enabledColor", ClickGuiModule, new JColor(121, 193, 255, 255)); //(0, 157, 255, 255));
	public static ColorSetting backgroundColor = new ColorSetting("bgColor", ClickGuiModule, new JColor(0, 0, 0, 20)); //(0, 121, 194, 255));
	public static ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", ClickGuiModule, new JColor(216, 216, 216, 255));
	public static ColorSetting outlineColor = new ColorSetting("settingsOutline", ClickGuiModule, new JColor(255, 255, 255, 255));
	public static ColorSetting fontColor = new ColorSetting("categoryColor", ClickGuiModule, new JColor(103, 167, 221, 255));
	public static NumberSetting opacity = new NumberSetting("opacity", ClickGuiModule, 200, 0, 255, 5);
	public static BooleanSetting showHud = new BooleanSetting("showHud", ClickGuiModule, true);
	
public ClickGuiModule() {
	super("clickGuiModule", "classic hud", Keyboard.KEY_RSHIFT, Category.CLIENT);
	this.addSettings(showHud,scrollMode,scrolls,animationSpeed,opacity,enabledColor,backgroundColor,settingBackgroundColor,outlineColor,fontColor);
	INSTANCE = this;
}

/** This uses minecraft's old "super secret" shaders, which means it could be modified to be a bunch of things in the future */
private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");

	public void onEnable() {
		Main.getInstance().clickGui.enterGUI();
	}

	public void onUpdate() {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			this.setToggled(!toggled);
		}
		
	}

	public void onDisable() {
		}
}