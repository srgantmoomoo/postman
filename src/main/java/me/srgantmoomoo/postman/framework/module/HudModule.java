package me.srgantmoomoo.postman.framework.module;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.Main;

/**
 * @author lukflug
 */

public abstract class HudModule extends Module {

	protected FixedComponent component;
	protected Point position;
	
	public HudModule (String title, String description, Point defaultPos, Category category) {
		super(title, description, Keyboard.KEY_NONE, category);
		position = defaultPos;
	}
	
	public abstract void populate (Theme theme);

	public FixedComponent getComponent() {
		return component;
	}
	
	public void resetPosition() {
		component.setPosition(Main.INSTANCE.clickGui.guiInterface,position);
	}
}