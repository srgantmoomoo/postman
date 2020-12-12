package me.srgantmoomoo.postman.module.modules.client;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Written by @SrgantMooMoo on November 7th, 2020.
 */
public class KeyStrokes extends Module {
	public NumberSetting xaxis = new NumberSetting("xaxis", 60, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", 160, -1000, 1000, 10);
	public boolean enabled;
	
	public KeyStrokes() {
		super("keyStrokes", "key strooookkkesss", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(xaxis, yaxis);
	}
	
	public static enum KeyStrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_SHFT(Key.W, Key.A, Key.S, Key.D, Key.SHFT, Key.JMP);
		
		private final Key[] keys;
		private int width;
		private int height;

		private KeyStrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
	}
	
	private static class Key {
		
		private static final Key W = new Key("w", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
		private static final Key A = new Key("a", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("s", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("d", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
		
		private static final Key SHFT = new Key("shft", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 41, 28, 18);
		private static final Key JMP = new Key("jmp", Minecraft.getMinecraft().gameSettings.keyBindJump, 31, 41, 28, 18);
		
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return height;
		}
		
		public String getName() {
			return name;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	private KeyStrokesMode mode = KeyStrokesMode.WASD_SHFT;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRenderer;
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			if(enabled) {
		
		GL11.glPushMatrix();
		
		boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
		
		GL11.glDisable(GL11.GL_BLEND);
		
		
		for(Key key : mode.getKeys()) {
			
			int textWidth = fr.getStringWidth(key.getName());
			
			Gui.drawRect(
					(int) (xaxis.getValue() + key.getX()), 
					(int) (yaxis.getValue() + key.getY()), 
					(int) (xaxis.getValue() + key.getX()) + key.getWidth(), 
					(int) (yaxis.getValue() + key.getY()) + key.getHeight(), 
					key.isDown() ? Color.WHITE.getRGB() : new Color(0, 0, 0, 102).getRGB()
					);
			
			fr.drawString(
					key.getName(), 
					(int) (xaxis.getValue() + key.getX() + key.getWidth() /2 - textWidth / 2), 
					(int) (yaxis.getValue() + key.getY() + key.getHeight() / 2 - 4), 
					key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
			
		}
		
		
		if(blend) {
			GL11.glEnable(GL11.GL_BLEND);
		}
		
		GL11.glPopMatrix();
			}
		}
	}
	
	public void onEnable() {
		super.onEnable();
		enabled = true;
	}
	
	public void onDisable() {
		super.onDisable();
		enabled = false;
	}

}
