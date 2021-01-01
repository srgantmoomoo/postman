package me.srgantmoomoo.postman.module.modules;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.tabgui.DefaultRenderer;
import com.lukflug.panelstudio.tabgui.TabGUI;
import com.lukflug.panelstudio.tabgui.TabGUIContainer;
import com.lukflug.panelstudio.tabgui.TabGUIItem;
import com.lukflug.panelstudio.tabgui.TabGUIRenderer;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.modules.client.HudModule;
import me.srgantmoomoo.postman.ui.clickgui.ClickGui;
import me.srgantmoomoo.postman.ui.clickgui.ClickGuiModule;

public class Tab extends HudModule {
	
	public Tab() {
		super ("tabGui", "yeeyee", new Point(0,0));
	}
	
	@Override
	public void populate (Theme theme) {
		TabGUIRenderer renderer=new DefaultRenderer(new SettingsColorScheme(ClickGuiModule.enabledColor,ClickGuiModule.backgroundColor,ClickGuiModule.settingBackgroundColor,ClickGuiModule.backgroundColor,ClickGuiModule.fontColor,ClickGuiModule.opacity),ClickGui.HEIGHT,5,Keyboard.KEY_UP,Keyboard.KEY_DOWN,Keyboard.KEY_LEFT,Keyboard.KEY_RIGHT,Keyboard.KEY_RETURN);
		TabGUI component=new TabGUI("tabGui",renderer,new Animation() {
			@Override
			protected int getSpeed() {
				return (int) ClickGuiModule.animationSpeed.getValue();
			}
		},position,75);
		for (Category category: Category.values()) {
			TabGUIContainer tab=new TabGUIContainer(category.name(),renderer,new SettingsAnimation(ClickGuiModule.animationSpeed));
			component.addComponent(tab);
			for (Module module: ModuleManager.getModulesInCategory(category)) {
				tab.addComponent(new TabGUIItem(module.getName(),module));
			}
		}
		this.component=component;
	}
}
