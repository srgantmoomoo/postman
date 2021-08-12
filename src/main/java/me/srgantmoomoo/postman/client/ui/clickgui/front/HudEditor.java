package me.srgantmoomoo.postman.client.ui.clickgui.front;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import net.minecraft.util.ResourceLocation;

public class HudEditor extends Module {
	public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);
	
	public HudEditor() {
		super("hudEditor", "descrp", Keyboard.KEY_NONE, Category.HUD);
		this.addSettings(exitToClickGui);
	}
	private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");
	
	@Override
	public void onEnable() {
		Main.clickGui.enterHUDEditor();
		if(ClickGuiModule.INSTANCE.blur.isEnabled()) 
			mc.entityRenderer.loadShader(shader);
	}
	
	@Override
	public void onUpdate() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if(exitToClickGui.isEnabled()) {
				this.disable(); 
				Main.clickGui.enterGUI(); 
			}else {
				this.disable();
			}
		}
		
	}
	
	@Override
	public void onDisable() {
		mc.entityRenderer.getShaderGroup().deleteShaderGroup();
	}
}
