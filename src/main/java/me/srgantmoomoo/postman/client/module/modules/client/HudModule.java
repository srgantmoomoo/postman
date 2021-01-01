package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

//originally written by @lukflug... ive got to properly credit these better lmao
public abstract class HudModule extends Module {
	protected FixedComponent component;
	protected Point position;
	
	public HudModule (String name, String description, Point defaultPos) {
		super(name, description, Keyboard.KEY_NONE, Category.CLIENT);
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
