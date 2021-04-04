package me.srgantmoomoo.postman.client.ui;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.setting.Setting;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.KeybindSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
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
		super ("tabGui", "cloocky clocky.", Keyboard.KEY_NONE, Category.CLIENT);
		MinecraftForge.EVENT_BUS.register(this);
		toggled = true;
		tab = true;
	}
	
	public static int rainbow(int delay) {
		   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		   rainbowState %= -360;
	       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if(Keyboard.getEventKeyState()) {
				int keyCode = Keyboard.getEventKey();
	        if (keyCode == Keyboard.KEY_UP) {
	            if (!expanded) {
	            	if(currentTab >= Category.values().length - 1) currentTab = 0;
					else currentTab--;
	            }else {
	            	if(category.moduleIndex >= modules.size() - 1) category.moduleIndex = 0;
					else category.moduleIndex--;
	            }
	        }
	 
	        if (keyCode == Keyboard.KEY_DOWN) {
	            if (!expanded) {
	            	if(currentTab >= Category.values().length - 1) currentTab = 0;
					else currentTab++;
	            }else {
	            	if(category.moduleIndex >= modules.size() - 1) category.moduleIndex = 0;
					else category.moduleIndex++;
	            }
	        }
	        
	        if (keyCode == Keyboard.KEY_RIGHT) {
	        	Module module = modules.get(category.moduleIndex);
	            if (!expanded) {
	                expanded = true;
	                category.moduleIndex = 0;
	            } else {
	                module.toggle();
	            }
	        }
	        
	        if (keyCode == Keyboard.KEY_LEFT) {
	            expanded = false;
	        }
	        
	        if (keyCode == Keyboard.KEY_RETURN) {
	        	Module module = modules.get(category.moduleIndex);
	            if (expanded) {
	                module.toggle();
            	}
        	}
		}
	}
	
		/*@SubscribeEvent
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
			if(!tab) tab = true;
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
		}*/
}

