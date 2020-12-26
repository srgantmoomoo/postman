package me.srgantmoomoo.postman.ui;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.ColorSetting;
import me.srgantmoomoo.postman.settings.KeybindSetting;
import me.srgantmoomoo.postman.settings.ModeSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import me.srgantmoomoo.postman.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/*
 * Written by @SrgantMooMoo on November 6th, 2020.
 */

public class TabGui extends Module {
	
	public int currentTab;
	public boolean expanded;
	public boolean tab;
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public TabGui() {
		super ("tabGui", "cloocky clocky", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
		tab = true;
	}
	
	public static int rainbow(int delay) {
		   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		   rainbowState %= -360;
	       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}
	
	@SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
		event.getType();
		if (!event.getType().equals(ElementType.TEXT)) {
			return;
		}
		
		FontRenderer fr = mc.fontRenderer;
		ScaledResolution sr = new ScaledResolution(mc);
		
		if(tab) {
	    			Gui.drawRect(sr.getScaledWidth() - 1, 59, sr.getScaledWidth() - 60, 129, 0x20000000);
			
			Gui.drawRect(sr.getScaledWidth() - 1, 60 + currentTab * 14 - 1, sr.getScaledWidth() - 60, 62 + currentTab * 14 + 11, 0xff79c2ec);
			
			int count = 0;
			for(Category c : Category.values()) {
						fr.drawStringWithShadow("<" + " " + c.name, sr.getScaledWidth() - 57, 62 + count * 14, 0xffffffff);
				count++;
			}
			
			/* Category category = Category.values()[currentTab];
			for (Module mod : Main.moduleManager.getModuleList()) {
			 if(category.name.equals("player")) {
				 fr.drawStringWithShadow(category.name, sr.getScaledWidth() - 47 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				}else {
					if(category.name.equals("render")) {
					fr.drawStringWithShadow(category.name, sr.getScaledWidth() - 61 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				
				}else {
					if(category.name.equals("pvp")) {
						fr.drawStringWithShadow(category.name, sr.getScaledWidth() - 75 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				}else {
					if(category.name.equals("exploits")) {
						fr.drawStringWithShadow(category.name, sr.getScaledWidth() - 89 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				
				}else {
					if(category.name.equals("client")) {
						fr.drawStringWithShadow(category.name, sr.getScaledWidth() - 103 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				}
			}
				
		}
				}
				}
				} */
			} 
	if(expanded) {
		Category category = Category.values()[currentTab];
		List<Module> modules = ModuleManager.getModulesByCategory(category);
		int count = 0;
		if (modules.size() == 0)
			return;
		Gui.drawRect(sr.getScaledWidth() - 139, 59, sr.getScaledWidth() - 61, 59 + modules.size() * 14 , 0x20000000);
		Gui.drawRect(sr.getScaledWidth() - 61, 60 + category.moduleIndex * 14 - 1, sr.getScaledWidth() - 139, 62 + category.moduleIndex * 14 + 11, 0xff79c2ec);
		
			count = 0;
			for(Module m : modules) {
				if (!m.getName().equals("Esp2dHelper")) {
				fr.drawStringWithShadow(m.getName(), sr.getScaledWidth() - 136, 62 + count * 14, 0xffffffff);
				}
				
				if(count == category.moduleIndex && m.expanded) {
					
					if(!m.settings.isEmpty()) {
						Gui.drawRect(sr.getScaledWidth() - 140, 59, sr.getScaledWidth() - 226, 59 + m.settings.size() * 14, 0x20000000);
						Gui.drawRect(sr.getScaledWidth() - 140, 60 + m.index * 14 - 1, sr.getScaledWidth() - 226, 62 + m.index * 14 + 11, m.settings.get(m.index).focused ? 0xff67a7dd : 0xff79c2ec);
					}
					
					int index = 0;
					for(Setting setting : m.settings) {
						
						if(setting instanceof BooleanSetting) {
							BooleanSetting bool = (BooleanSetting) setting;
							fr.drawStringWithShadow(setting.name + ":" + " " + (bool.isEnabled() ? "on" : "off"), sr.getScaledWidth() - 224, 62 + index * 14, 0xffffffff);
						}
						
						if(setting instanceof NumberSetting) {
							NumberSetting number = (NumberSetting) setting;
							fr.drawStringWithShadow(setting.name + ":" + " " + number.getValue(), sr.getScaledWidth() - 224, 62 + index * 14, 0xffffffff);
						}
						
						if(setting instanceof ModeSetting) {
							ModeSetting mode = (ModeSetting) setting;
							fr.drawStringWithShadow(setting.name + ":" + " " + mode.getMode(), sr.getScaledWidth() - 224, 62 + index * 14, 0xffffffff);
						}
						
						if(setting instanceof KeybindSetting) {
							KeybindSetting keyBind = (KeybindSetting) setting;
							fr.drawStringWithShadow(setting.name + ":" + " " + Keyboard.getKeyName(keyBind.code), sr.getScaledWidth() - 224, 62 + index * 14, 0xffffffff);
						}
						
						fr.drawStringWithShadow(setting.name, sr.getScaledWidth() - 224, 62 + index * 14, 0xffffffff);
						index++;
					}
				}
				
				/* for(Module s : modules) {
				Module module = modules.get(category.moduleIndex);
				fr.drawStringWithShadow(module.getName(), sr.getScaledWidth() - 125 + currentTab * 14 - 1, 51 + currentTab * 14 + 11, 0xffffa6f1);
				
				} */
				
				if(!m.getName().equals("Esp2dHelper") && m.toggled) 
					Gui.drawRect(sr.getScaledWidth() - 139, 60 + count * 14, sr.getScaledWidth() - 138, 72 + count * 14, 0xffffffff);
				if (!m.getName().equals("Esp2dHelper")) {
				fr.drawStringWithShadow(m.getName(), sr.getScaledWidth() - 136, 62 + count * 14, 0xffffffff);
				count++;
				}
			}
			
			//description
			/*Module module = modules.get(category.moduleIndex);
				fr.drawStringWithShadow(module.getDescription(), 1, sr.getScaledHeight() - 10, 0xfffffacd);*/
				}
			}
	
		@SubscribeEvent
		public void key(KeyInputEvent e) {

		Category category = Category.values()[currentTab];
		List<Module> modules = ModuleManager.getModulesByCategory(category);
		if(Keyboard.getEventKeyState()) {
			int keyCode = Keyboard.getEventKey();
		
		if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
			Module module = modules.get(category.moduleIndex);
			
			if(!module.settings.isEmpty() && module.settings.get(module.index).focused && module.settings.get(module.index) instanceof KeybindSetting) {
				if(keyCode != Keyboard.KEY_RETURN && keyCode != Keyboard.KEY_UP && keyCode != Keyboard.KEY_DOWN && keyCode != Keyboard.KEY_LEFT && keyCode != Keyboard.KEY_RIGHT && keyCode != Keyboard.KEY_ESCAPE) {
				KeybindSetting keyBind = (KeybindSetting)module.settings.get(module.index);
				
				keyBind.code = keyCode;
				keyBind.focused = false;
				
				return;
				}
			}
		}
			
		if(keyCode == Keyboard.KEY_UP) {
			if(expanded) {
				if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
					Module module = modules.get(category.moduleIndex);
					
					if(!module.settings.isEmpty()) {				
					if(module.settings.get(module.index).focused) {
						
						Setting setting = module.settings.get(module.index);
						
						if(setting instanceof NumberSetting) {
							((NumberSetting)setting).increment(true);
						}
						
					}else {
						if(module.index <= 0) {
							module.index = module.settings.size() - 1;
							}else
								module.index--;
						}
					}
				}else {
				
				if(category.moduleIndex <= 0) {
					category.moduleIndex = modules.size() - 1;
				}else
					category.moduleIndex--;
				}
			}else {
					if(tab) {
				if(currentTab <= 0) {
					currentTab = Category.values().length - 1;
				}else
					currentTab--;
					}
				
			}
		}
		
		if(keyCode == Keyboard.KEY_DOWN) {
			tab = true;
			if (expanded) {
				if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
					Module module = modules.get(category.moduleIndex);
					
					if(!module.settings.isEmpty()) {	
					if(module.settings.get(module.index).focused) {
						
						Setting setting = module.settings.get(module.index);
						
						if(setting instanceof NumberSetting) {
							((NumberSetting)setting).increment(false);
						}
						
					}else {
						if(module.index >= module.settings.size() - 1) {
							module.index = 0;
							}else
								module.index++;
						}
					}
				}else {	
				
				if(category.moduleIndex >= modules.size() - 1) {
					category.moduleIndex = 0;
					}else
						category.moduleIndex++;
				}
			}else {
					if(tab) {
				if(currentTab >= Category.values().length - 1) {
					currentTab = 0;
					}else
						currentTab++;
					}
			}
		}
		
		if(keyCode == Keyboard.KEY_RIGHT) {
			if(!expanded) {
				tab = false;
				expanded = false;
			}else 
				if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
					Module module = modules.get(category.moduleIndex);
					
					Setting setting = module.settings.get(module.index);
					if(setting instanceof BooleanSetting) {
						if(module.settings.get(module.index).focused) {
						((BooleanSetting)setting).toggle();
						}
					}
					if(setting instanceof ModeSetting) {
						if(module.settings.get(module.index).focused) {
						((ModeSetting)setting).cycle();
							}
						}
					
					if(!module.settings.isEmpty()) {	
					if(module.settings.get(module.index).focused) {
						
					}else {
						modules.get(category.moduleIndex).expanded = false;
						}
					}
				}else
					expanded = false;
		}
		
		
		
		
		if(keyCode == Keyboard.KEY_LEFT) {
			if(tab) {
				if(expanded && modules.size() !=0) {
					Module module = modules.get(category.moduleIndex);
					
					if(!module.getName().equals("tabGui") && !module.getName().equals("Esp2dHelper")) {
					if(!module.expanded && !module.settings.isEmpty()) 
						module.expanded = true;
					}
					
					
					if(expanded && !modules.isEmpty() && module.expanded) {
						if(!module.settings.isEmpty()) {
						Setting setting = module.settings.get(module.index);
						
						if(setting instanceof BooleanSetting) {
							if(module.settings.get(module.index).focused) {
							((BooleanSetting)setting).toggle();
							}
						}
						if(setting instanceof ModeSetting) {
							if(module.settings.get(module.index).focused) {
							((ModeSetting)setting).cycle();
								}
							}
						}
					}
				}else {
					expanded = true;
				}
			}
			}
		
		if(keyCode == Keyboard.KEY_RETURN) {
			if(tab) {
				if(expanded && modules.size() !=0) {
					Module module = modules.get(category.moduleIndex);
						if(!module.getName().equals("tabGui") && !module.getName().equals("Esp2dHelper")) {
							if(!module.expanded && !module.settings.isEmpty())
							module.toggle();
						}
					
					if(module.expanded && !module.settings.isEmpty()) {
						module.settings.get(module.index).focused = !module.settings.get(module.index).focused;
					}
				}
			}
				}
			}
		}
}

