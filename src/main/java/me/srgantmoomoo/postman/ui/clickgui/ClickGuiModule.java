package me.srgantmoomoo.postman.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.util.ResourceLocation;

public class ClickGuiModule extends Module {
	private static Module ClickGuiModule;

	public ClickGuiModule INSTANCE;
		
	public static NumberSetting animationSpeed = new NumberSetting("animationSpeed", ClickGuiModule, 150, 0, 1000, 100);
	public static ColorSetting enabledColor = new ColorSetting("enabledColor", ClickGuiModule, new JColor(155, 0, 0, 255));
	public static ColorSetting backgroundColor = new ColorSetting("backgroundColor", ClickGuiModule, new JColor(0, 121, 194, 255));
	public static ColorSetting settingBackgroundColor = new ColorSetting("settingBackgroundColor", ClickGuiModule, new JColor(255, 0, 0, 255));
	public static ColorSetting outlineColor = new ColorSetting("outlineColor", ClickGuiModule, new JColor(0, 121, 194, 255));
	public static ColorSetting fontColor = new ColorSetting("fontColor", ClickGuiModule, new JColor(255, 0, 0, 255));
	public static NumberSetting opacity = new NumberSetting("opacity", ClickGuiModule, 90, 50, 255, 10);
	public static BooleanSetting showHud = new BooleanSetting("showHud", ClickGuiModule, true);
	
public ClickGuiModule() {
	super("clickGui", "classic hud", Keyboard.KEY_RSHIFT, Category.CLIENT);
	this.addSettings(animationSpeed,opacity,enabledColor,backgroundColor,settingBackgroundColor,outlineColor,fontColor,showHud);
	INSTANCE = this;
}

/** This uses minecraft's old "super secret" shaders, which means it could be modified to be a bunch of things in the future */
private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");

	public void onEnable(){
		Main.getInstance().clickGui.enterGUI();
	}

	public void onUpdate(){

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			this.setToggled(!toggled);
		}
		
	}

	public void onDisable(){
		}
}