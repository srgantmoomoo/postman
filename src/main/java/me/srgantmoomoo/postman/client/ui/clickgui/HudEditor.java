package me.srgantmoomoo.postman.client.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;

public class HudEditor extends Module {
	public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);
	
	public HudEditor() {
		super("hudEditor", "descrp", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(exitToClickGui);
	}
	
	public void onEnable() {
		Main.getInstance().clickGui.enterHUDEditor();
		disable();
	}
	
	public void onUpdate() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if(exitToClickGui.isEnabled()) { this.setToggled(!toggled); Main.getInstance().clickGui.enterGUI(); }
			else { this.setToggled(!toggled); }
		}
		
	}
	
	public void onDisable() {
	}
}
