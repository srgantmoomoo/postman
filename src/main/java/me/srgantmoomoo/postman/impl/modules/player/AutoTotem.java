package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class AutoTotem extends Module {
	
	public AutoTotem() {
		super ("autoTotem", "automatically places totem in ur offhand.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	private boolean switching = false;
    private int lastSlot;

    @Override
    public void onUpdate() {

        if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {

            if (switching) {
            	swapTotem(lastSlot, 2);
                return;
            }

            if (mc.player.getHeldItemOffhand().getItem() == Items.AIR || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE
            		&& !Main.INSTANCE.moduleManager.getModuleByName("SmartOffHand").isToggled()
            		|| mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL && !Main.INSTANCE.moduleManager.getModuleByName("SmartOffHand").isToggled()) {
            	swapTotem(getTotem(), 0);
            }

        }

    }

    private int getTotem() {
        if (Items.TOTEM_OF_UNDYING == mc.player.getHeldItemOffhand().getItem()) return -1;
        for(int i = 45; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    public void swapTotem(int slot, int step) {
        if (slot == -1) return;
        if (step == 0) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        }
        if (step == 1) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            switching = true;
            lastSlot = slot;
        }
        if (step == 2) {
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            switching = false;
        }

        mc.playerController.updateController();
    }
}

