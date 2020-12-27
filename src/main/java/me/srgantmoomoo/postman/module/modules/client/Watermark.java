package me.srgantmoomoo.postman.module.modules.client;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.api.util.Refrence;
import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Watermark extends Module {
	public NumberSetting xaxis = new NumberSetting("xaxis", this, 0, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", this, 0, -1000, 1000, 10);
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public Watermark() {
		super ("watermark", "yeeyee", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(xaxis,yaxis);
	}
	
	ScaledResolution sr = new ScaledResolution(mc);
	FontRenderer fr = mc.fontRenderer;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
	if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
		if(on) {
		fr.drawStringWithShadow(Refrence.NAME, (float) (1 + xaxis.getValue()), (float) (2 + yaxis.getValue()), 0xffffffff);
		fr.drawStringWithShadow(Refrence.VERSION, (float) (42 + xaxis.getValue()), (float) (2 + yaxis.getValue()), 0xff79c2ec); //0xff009dff
			}
		}
	}
	
	public void onEnable() {
		super.onEnable();
		on = true;
	}
	
	public void onDisable() {
		super.onDisable();
		on = false;
	}
}
	
// ADD COLOR SETTING TO HERE THIS TEUHTUIWEHTBLKJHDFSBWTYRCSA
	/*
	public Watermark() {
		super("watermark", "s", new Point(1,2));
	}
	
	@Override
	public void populate (Theme theme) {
		component=new ListComponent(getName(),theme.getPanelRenderer(),position,new WatermarkList());
	}
	
	
	private class WatermarkList implements HUDList {
		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			return "postman "+ Refrence.VERSION;
		}

		@Override
		public Color getItemColor(int index) {
			return color.getValue();
		}

		@Override
		public boolean sortUp() {
			return false;
		}

		@Override
		public boolean sortRight() {
			return false;
		}
	}
}*/
