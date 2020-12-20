package me.srgantmoomoo.postman.ui.clickgui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.hud.HUDPanel;
import com.lukflug.panelstudiomc.GLInterface;
import com.lukflug.panelstudiomc.MinecraftHUDGUI;

import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.modules.client.ColorMain;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.postman.settings.Setting;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.EnumSetting;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.NumberSetting;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.settings.ToggleableContainer;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ClickGui extends MinecraftHUDGUI {
	public static final int WIDTH=100,HEIGHT=12,DISTANCE=10,HUD_BORDER=2;
	private final Toggleable colorToggle;
	public final GUIInterface guiInterface;
	public final HUDClickGUI gui;
	private final Theme theme;
	
	public ClickGui() {
		
		theme=new GameSenseTheme(new SettingsColorScheme(ClickGuiModule.enabledColor,ClickGuiModule.backgroundColor,ClickGuiModule.settingBackgroundColor,ClickGuiModule.outlineColor,ClickGuiModule.fontColor,ClickGuiModule.opacity),HEIGHT,2);
		colorToggle=new Toggleable() {
			@Override
			public void toggle() {
				//ColorMain.colorModel.increment();
			}
			
			@Override
			public boolean isOn() {
				return ColorMain.colorModel.getMode().equals("HSB");
			}
		};
		
		guiInterface=new GUIInterface() {
			@Override
			public void drawString(Point pos, String s, Color c) {
				FontRenderer fr = mc.fontRenderer;
				GLInterface.end();
				int x=pos.x+2, y=pos.y+1;
				fr.drawStringWithShadow(s,(float)x,(float)y,0xffffff);
				GLInterface.begin();
			}
			
			@Override
			public int getFontWidth(String s) {
				FontRenderer fr = mc.fontRenderer;
				return (int)Math.round(fr.getStringWidth(s))+4;
			}

			@Override
			public int getFontHeight() {
				FontRenderer fr = mc.fontRenderer;
				return (int)Math.round(((Interface) fr).getFontHeight())+2;
			}
			
			@Override
			public String getResourcePrefix() {
				return "gamesense:gui/";
			}
		};
		
		gui=new HUDClickGUI(guiInterface);
		Toggleable hudToggle=new Toggleable() {
			@Override
			public void toggle() {
			}

			@Override
			public boolean isOn() {
				return gui.isOn(); //&& ClickGuiModule.showHUD.isOn();
			}
		};
		
		//for (Module module: ModuleManager.modules) {
			//if (module instanceof HUDModule) {
				//((HUDModule)module).populate(theme);
				//gui.addHUDComponent(new HUDPanel(((HUDModule)module).getComponent(),theme.getPanelRenderer(),module,new SettingsAnimation((NumberSetting)ClickGuiModule.animationSpeed),hudToggle,HUD_BORDER));
			//}
		//}
		
		Point pos=new Point(DISTANCE,DISTANCE);
			for(Category category : Category.values()) {
			DraggableContainer panel=new DraggableContainer(category.name,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(ClickGuiModule.animationSpeed),new Point(pos),WIDTH) {
				@Override
				protected int getScrollHeight (int childHeight) {
					//if (ClickGuiModule.scrolling.getValue().equals("Screen")) {
						//return childHeight;
					//}
					return Math.min(childHeight,Math.max(HEIGHT*4,ClickGui.this.height-getPosition(guiInterface).y-renderer.getHeight()-HEIGHT));
				}
			};
			gui.addComponent(panel);
			pos.translate(WIDTH+DISTANCE,0);
			//for (Module module: ModuleManager.getModulesInCategory(category)) {
				//addModule(panel,module);
			//}
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX,mouseY,partialTicks);
        int scroll=Mouse.getDWheel();
        if (scroll!=0) {
        	//if (ClickGuiModule.scrolling.getValue().equals("Screen")) {
	        	//for (FixedComponent component: gui.getComponents()) {
	        		//if (!(component instanceof HUDPanel)) {
		        		//Point p=component.getPosition(guiInterface);
		        		//if (scroll>0) p.translate(0,5);
		        		//else p.translate(0,-5);
		        		//component.setPosition(guiInterface,p);
	        		//}
	        	//}
        	//}
        	if (scroll>0) gui.handleScroll(-5);
        	else gui.handleScroll(5);
        }
    }
	
	private void addModule (CollapsibleContainer panel, Module module) {
		CollapsibleContainer container;
		container=new ToggleableContainer(module.getName(),theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation((NumberSetting)ClickGuiModule.animationSpeed),module);
		panel.addComponent(container);
		Main.getInstance();
		for (Setting property: Main.settingsManager.getSettingsByMod(module)) {
			if (property instanceof BooleanSetting) {
				container.addComponent(new BooleanComponent(property.name,theme.getComponentRenderer(),(Toggleable) property));
			} else if (property instanceof me.srgantmoomoo.postman.settings.NumberSetting) {
				container.addComponent(new NumberComponent(property.name,theme.getComponentRenderer(),(NumberSetting)property,((NumberSetting)property).getMinimumValue(),((NumberSetting)property).getMaximumValue()));
			} else if (property instanceof ModeSetting) {
				container.addComponent(new EnumComponent(property.name,theme.getComponentRenderer(),(EnumSetting)property));
			} //else if (property instanceof ColorSetting) {
				//container.addComponent(new SyncableColorComponent(theme,(ColorSetting)property,colorToggle,new SettingsAnimation(ClickGuiModule.animationSpeed)));
			//}
		}
		//container.addComponent(new KeybindSetting(theme.getComponentRenderer(),module));
	}
	
	public static void renderItem (ItemStack item, Point pos) {
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
		GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPopAttrib();
		GlStateManager.enableDepth();
		GlStateManager.disableAlpha();
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(item,pos.x,pos.y);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer,item,pos.x,pos.y);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0F;
        GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GLInterface.begin();
	}
	
	public static void renderEntity (EntityLivingBase entity, Point pos, int scale) {
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
		GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPopAttrib();
		GlStateManager.enableDepth();
		GlStateManager.disableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.color(1,1,1,1);
        GuiInventory.drawEntityOnScreen(pos.x,pos.y,scale,28,60,entity);
        GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GLInterface.begin();
	}
	
	@Override
	protected HUDClickGUI getHUDGUI() {
		return gui;
	}

	@Override
	protected GUIInterface getInterface() {
		return guiInterface;
	}

	@Override
	protected int getScrollSpeed() {
		return 5;
	}
}