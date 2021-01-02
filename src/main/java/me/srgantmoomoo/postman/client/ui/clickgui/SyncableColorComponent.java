package me.srgantmoomoo.postman.client.ui.clickgui;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.FocusableComponent;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.settings.ColorComponent;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import net.minecraft.util.text.TextFormatting;

public class SyncableColorComponent extends ColorComponent {
	public SyncableColorComponent (Theme theme, ColorSetting setting, Toggleable colorToggle, Animation animation) {
		super(TextFormatting.BOLD+setting.name,null,theme.getContainerRenderer(),animation,theme.getComponentRenderer(),setting,false,true,colorToggle);
		if (setting!=ClickGuiModule.enabledColor) addComponent(new SyncButton(theme.getComponentRenderer()));
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
				setting.setValue(ClickGuiModule.enabledColor.getColor());
				setting.setRainbow(ClickGuiModule.enabledColor.getRainbow());
			}
		}
	}
}
