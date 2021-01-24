package me.srgantmoomoo.postman.client.ui.clickgui;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.opengl.GL11;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.hud.HUDPanel;
import com.lukflug.panelstudio.mc12.GLInterface;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.KeybindComponent;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.api.util.font.FontUtils;
import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.HudModule;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.client.ColorMain;
import me.srgantmoomoo.postman.client.setting.Setting;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.KeybindSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
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
	private Theme theme;
	
	public ClickGui() {
		if(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).theme.is("new"));
			theme=new PostmanTheme(new SettingsColorScheme(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).enabledColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).backgroundColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).settingBackgroundColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).outlineColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).fontColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).opacity),HEIGHT,2);
			
			if(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).theme.is("old"));
			theme=new PostmanTheme(new SettingsColorScheme(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).enabledColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).backgroundColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).settingBackgroundColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).outlineColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).fontColor,((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).opacity),HEIGHT,2);
			colorToggle=new Toggleable() {
				@Override
				public void toggle() {
					ColorMain.colorModel.increment();
				}
				
				@Override
				public boolean isOn() {
					return ColorMain.colorModel.is("RGB");
				}
			};
		guiInterface=new GUIInterface(true) {
			@Override
			public void drawString(Point pos, String s, Color c) {
				GLInterface.end();
				int x=pos.x+2, y=pos.y+1;
				FontUtils.drawStringWithShadow(false,s,x,y,new JColor(c));
				GLInterface.begin();
			}
			
			@Override
			public int getFontWidth(String s) {
				return Math.round(FontUtils.getStringWidth(false,s))+4;
			}

			@Override
			public int getFontHeight() {
				return Math.round(FontUtils.getFontHeight(false))+2;
			}

			@Override
			protected String getResourcePrefix() {
				return "psotman:gui/";
			}
		};
		gui=new HUDClickGUI(guiInterface,null) {
			@Override
			public void handleScroll (int diff) {
				super.handleScroll(diff);
				if (((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).scrollMode.is("screen")) {
					for (FixedComponent component: components) {
		        		if (!hudComponents.contains(component)) {
			        		Point p=component.getPosition(guiInterface);
			        		p.translate(0,-diff);
			        		component.setPosition(guiInterface,p);
		        		}
		        	}
				}
			}
		};
		Toggleable hudToggle=new Toggleable() {
			@Override
			public void toggle() {
				render();
			}

			@Override
			public boolean isOn() {
				return hudEditor;
			}
		};
		
		for (Module module: ModuleManager.getModules()) {
			if (module instanceof HudModule) {
				((HudModule)module).populate(theme);
				gui.addHUDComponent(new HUDPanel(((HudModule)module).getComponent(),theme.getPanelRenderer(),module,new SettingsAnimation(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).animationSpeed),hudToggle,HUD_BORDER));
			}
		}
		Point pos=new Point(DISTANCE,DISTANCE);
		for (Category category: Category.values()) {
			DraggableContainer panel=new DraggableContainer(category.name,null,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).animationSpeed),null,new Point(pos),WIDTH) {
				@Override
				protected int getScrollHeight (int childHeight) {
					if (((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).scrollMode.equals("screen")) {
						return childHeight;
					}
					return Math.min(childHeight,Math.max(HEIGHT*4,ClickGui.this.height-getPosition(guiInterface).y-renderer.getHeight(open.getValue()!=0)-HEIGHT));
				}
			};
			gui.addComponent(panel);
			pos.translate(WIDTH+DISTANCE,0);
			for (Module module: ModuleManager.getModulesInCategory(category)) {
				addModule(panel,module);
			}
		}
	}
	
	private void addModule (CollapsibleContainer panel, Module module) {
		CollapsibleContainer container=new CollapsibleContainer(module.getName(),module.getDescription(),theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).animationSpeed),module);
		if(!module.getName().equals("Esp2dHelper")) {
		panel.addComponent(container);
		for (Setting property: module.settings) {
			if (property instanceof BooleanSetting) {
				container.addComponent(new BooleanComponent(property.name,null,theme.getComponentRenderer(),(BooleanSetting)property));
			} else if (property instanceof NumberSetting) {
				container.addComponent(new NumberComponent(property.name,null,theme.getComponentRenderer(),(NumberSetting)property,((NumberSetting)property).getMinimun(),((NumberSetting)property).getMaximum()));
			}  else if (property instanceof ModeSetting) {
				container.addComponent(new EnumComponent(property.name,null,theme.getComponentRenderer(),(ModeSetting)property));
			}	else if (property instanceof ColorSetting) { 
				container.addComponent(new SyncableColorComponent(theme,(ColorSetting)property,colorToggle,new SettingsAnimation(((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).animationSpeed)));

			} else if (property instanceof KeybindSetting) {
				container.addComponent(new KeybindComponent(theme.getComponentRenderer(),(KeybindSetting)property));
			}
		}
		}
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
		return (int) ((ClickGuiModule)ModuleManager.getModuleByName("clickGuiModule")).scrolls.getValue();
	}
}