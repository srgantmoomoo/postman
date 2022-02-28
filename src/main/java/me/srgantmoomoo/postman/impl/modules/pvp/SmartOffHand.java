package me.srgantmoomoo.postman.impl.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/*
 * i looked at a bit of salhack for some of the stuff used here o_0
 * SrgantMooMoo feb 14 2021 (valentines day, and im all a fucking lone :stronk_tone6: :')
 */

// rewritten by SrgantMooMoo on 03/24/2021 and 03/25/2021.

public class SmartOffHand extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "gap", "gap", "crystal", "totem");
	public NumberSetting health = new NumberSetting("health", this, 14, 0, 20, 1);
	public BooleanSetting check = new BooleanSetting("crystalCheck", this, true);
	
	public SmartOffHand() {
		super("smartOffHand", "smart, off. HAND.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode, health, check);
	}
	public String currentMode;

	@Override
	public void onEnable() {
		if(mc.player == null || mc.world == null) return;
		currentMode = mode.getMode();
	}
	
	private void SwitchOffHand(ModeSetting val) {
        Item item = getItem(val);
        
        if (mc.player.getHeldItemOffhand().getItem() != item) {
            int slot = getItemSlot(item);
            
            if (slot != -1) {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);
                
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();
                
            }
        }
    }
	
	private void SwitchOffHandTotem() {
        Item item = Items.TOTEM_OF_UNDYING;
        
        if (mc.player.getHeldItemOffhand().getItem() != item) {
            int slot = getItemSlot(item);
            
            if (slot != -1) {
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP,
                        mc.player);
                
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();
                
            }
        }
    }

    @EventHandler
    private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(event -> {
    	
    	if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiInventory)))
            return;
    	
    	if(check.isEnabled() && !crystalCheck()) {
    		mode.setMode(currentMode);
    		SwitchOffHand(mode);
    	}
    	if(check.isEnabled() && crystalCheck()) {
    		mode.setMode("totem");
        	SwitchOffHandTotem();
            return;
    	}
    	if(getHealthWithAbsorption() > health.getValue()) {
    		mode.setMode(currentMode);
    		SwitchOffHand(mode);
    	}else if (getHealthWithAbsorption() <= health.getValue()) {
        	mode.setMode("totem");
        	SwitchOffHandTotem();
            return;
        }
    		
    });
    
    private boolean crystalCheck() {
        for(Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityEnderCrystal && mc.player.getDistance(e) <= 12) {
                if ((AutoCrystal.calculateDamage(e.posX, e.posY, e.posZ, mc.player)) >= mc.player.getHealth()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static float getHealthWithAbsorption() {
        return mc.player.getHealth() + mc.player.getAbsorptionAmount();
    }
    
    public static int getItemSlot(Item input) {
        if (mc.player == null)
            return 0;

        for (int i = 0; i < mc.player.inventoryContainer.getInventory().size(); ++i) {
            if (i == 0 || i == 5 || i == 6 || i == 7 || i == 8)
                continue;

            ItemStack s = mc.player.inventoryContainer.getInventory().get(i);
            
            if (s.isEmpty())
                continue;
            
            if (s.getItem() == input) {
                return i;
            }
        }
        return -1;
    }
    
    public Item getItem(ModeSetting val) {
    	if(val.is("crystal")) return Items.END_CRYSTAL;
    	if(val.is("gap")) return Items.GOLDEN_APPLE;
    	if(val.is("totem")) return Items.TOTEM_OF_UNDYING;
        
        return Items.TOTEM_OF_UNDYING;
    }

}
