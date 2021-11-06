package me.srgantmoomoo.postman.api.util.render;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

// this is from gs

public class ColorMain extends Module {
	
	private static final Module ColorMain = null;
	public static final ModeSetting colorModel = new ModeSetting("penis right?", ColorMain, "HSB", "RGB", "HSB");
	
	public ColorMain() {
		super ("colorMain", "world of colors", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(colorModel);
	}
	
	public void setup() {
		ArrayList<String> tab = new ArrayList<>();
		tab.add("Black");
		tab.add("Dark Green");
		tab.add("Dark Red");
		tab.add("Gold");
		tab.add("Dark Gray");
		tab.add("Green");
		tab.add("Red");
		tab.add("Yellow");
		tab.add("Dark Blue");
		tab.add("Dark Aqua");
		tab.add("Dark Purple");
		tab.add("Gray");
		tab.add("Blue");
		tab.add("Aqua");
		tab.add("Light Purple");
		tab.add("White");
		ArrayList<String> models=new ArrayList<>();
		models.add("RGB");
		models.add("HSB");
	}

	public void onEnable() {
		this.disable();
	}
	
	private static TextFormatting settingToFormatting () {
			return TextFormatting.AQUA;
	}

	public static TextFormatting getEnabledColor() { return settingToFormatting(); }

	public static TextFormatting getDisabledColor() { return settingToFormatting(); }

}
