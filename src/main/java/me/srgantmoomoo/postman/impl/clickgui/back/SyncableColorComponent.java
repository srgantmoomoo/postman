package me.srgantmoomoo.postman.impl.clickgui.back;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.FocusableComponent;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.settings.ColorComponent;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.impl.clickgui.front.ClickGuiModule;
import net.minecraft.util.text.TextFormatting;

public class SyncableColorComponent extends ColorComponent {
	public SyncableColorComponent (Theme theme, ColorSetting setting, Toggleable colorToggle, Animation animation) {
		super(TextFormatting.BOLD+setting.name,null,theme.getContainerRenderer(),animation,theme.getComponentRenderer(),setting,true,true,colorToggle);
		if (setting!=((ClickGuiModule) Main.INSTANCE.moduleManager.getModuleByName("clickGui")).enabledColor) addComponent(new SyncButton(theme.getComponentRenderer()));
	}
	
	private class SyncButton extends FocusableComponent {
		public SyncButton (Renderer renderer) {
			super("Sync Color",null,renderer);
		}
		
		@Override
		public void render (Context context) {
			super.render(context);
			renderer.overrideColorScheme(overrideScheme);
			renderer.renderTitle(context,title,hasFocus(context),false);
			renderer.restoreColorScheme();
		}
		
		@Override
		public void handleButton (Context context, int button) {
			super.handleButton(context,button);
			if (button==Interface.LBUTTON && context.isClicked()) {
				setting.setValue(((ClickGuiModule) Main.INSTANCE.moduleManager.getModuleByName("clickGui")).enabledColor.getColor());
				setting.setRainbow(((ClickGuiModule) Main.INSTANCE.moduleManager.getModuleByName("clickGui")).enabledColor.getRainbow());
			}
		}
	}
}
