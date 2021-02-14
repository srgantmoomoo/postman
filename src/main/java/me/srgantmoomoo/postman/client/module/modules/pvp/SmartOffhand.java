package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.postman.api.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.api.util.world.EntityUtil;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/*
 * i looked at a bit of salhack for some of the stuff used here o_0
 * SrgantMooMoo feb 13 2021
 */
public class SmartOffHand extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "gap", "gap", "crystal");
	
	public SmartOffHand() {
		super("smartOffHand", "smart, off. HAND.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode);
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
                
                /// @todo: this might cause desyncs, we need a callback for windowclicks for transaction complete packet.
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0,
                        ClickType.PICKUP, mc.player);
                mc.playerController.updateController();
                
            }
        }
    }

    @EventHandler
    private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(p_Event -> {
        if (mc.currentScreen != null && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof SalGuiScreen)))
            return;
        
        if (/* player health / absorbtion */ < ToggleHealth.getValue()) {
            toggle();
            return;
        }
        
        SwitchOffHand(mode.index);
    });
    
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
        
        return Items.TOTEM_OF_UNDYING;
    }

    private String getItemName(ModeSetting val) {
    	if(val.is("crystal")) return "crystal";
    	if(val.is("gap")) return "gap";
        
        return "totem";
    }

}
