package me.srgantmoomoo.postman.module.modules.client;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public abstract class HudModule extends Module {
	protected FixedComponent component;
	protected Point position;
	
	public HudModule (String title, Point defaultPos) {
		super(title, "HudModule", Keyboard.KEY_NONE, Category.CLIENT);
		position=defaultPos;
	}
	
	public abstract void populate (Theme theme);

	public FixedComponent getComponent() {
		return component;
	}
	
	public void resetPosition() {
		component.setPosition(Main.getInstance().clickGui.guiInterface,position);
	}
}
