package me.srgantmoomoo.postman.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hey extends Module {
	public BooleanSetting hey = new BooleanSetting("hey", true);
	public BooleanSetting coords = new BooleanSetting("coords", true);
	public BooleanSetting right = new BooleanSetting("right", false);
	public NumberSetting xaxis = new NumberSetting("xaxis", 80, -1000, 1000, 10);
	public NumberSetting yaxis = new NumberSetting("yaxis", 100, -1000, 1000, 10);
	public boolean on;
	
	public Hey() {
		super("hey!", "classic hud", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(hey, coords, right, xaxis, yaxis);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();
	ScaledResolution sr = new ScaledResolution(mc);
	FontRenderer fr = mc.fontRenderer;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
	if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
		if(on) {
			if(right.isEnabled()) {
				
			//hey!
			if(hey.isEnabled()) {
			fr.drawStringWithShadow("hey" + " " + mc.player.getName() + "!", sr.getScaledWidth() - fr.getStringWidth("hey" + " " + mc.player.getName() + "!") - (float) xaxis.getValue(), (float) yaxis.getValue() - 10, 0xffffff);
				}
			
			//coords
			if(coords.isEnabled()) {
				fr.drawStringWithShadow("(x)" + mc.player.getPosition().getX() + " " + "(y)" + mc.player.getPosition().getY() + " " + "(z)" + mc.player.getPosition().getZ(), sr.getScaledWidth() - fr.getStringWidth("(x)" + mc.player.getPosition().getX() + " " + "(y)" + mc.player.getPosition().getY() + " " + "(z)" + mc.player.getPosition().getZ()) - (float) xaxis.getValue(), (float) yaxis.getValue(), 0xffffff);
			}
			}else {
				
				//hey!
				if(hey.isEnabled()) {
					
				fr.drawStringWithShadow("hey" + " " + mc.player.getName() + "!", (float) xaxis.getValue(), (float) yaxis.getValue() - 10, 0xffffff);
					}
				
				//coords
				if(coords.isEnabled()) {
				fr.drawStringWithShadow("(x)" + mc.player.getPosition().getX() + " " + "(y)" + mc.player.getPosition().getY() + " " + "(z)" + mc.player.getPosition().getZ(), (float) xaxis.getValue(), (float) yaxis.getValue(), 0xffffff);
				}
			}
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
