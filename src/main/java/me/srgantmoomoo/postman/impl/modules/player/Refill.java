package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.util.world.JTimer;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

public class Refill extends Module {
	public NumberSetting delay = new NumberSetting("delay", this, 500.0f, 0.0f, 2000.0f, 1.0f);

    private final JTimer timer = new JTimer();
    public boolean offHand;

	public Refill() {
		super("refill", "automatically refills stacks in ur hotbar and offHand.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(delay);
	}
	
	@Override
	public void onUpdate() {
		if (this.timer.hasReached((long)this.delay.getValue())) {
            if (mc.currentScreen instanceof GuiInventory) {
                return;
            }

            int toRefill = getHalfStack(mc.player);
            if (toRefill != -1) {
            	refill(mc, toRefill);
            }

            timer.reset();
        }
	}
	
    private int getHalfStack(EntityPlayerSP player) {
        offHand = mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE;
        if (offHand) {
            if (player.getHeldItemOffhand().getItem() != Items.AIR && player.getHeldItemOffhand().getCount() < player.getHeldItemOffhand().getMaxStackSize()
                    && (double) player.getHeldItemOffhand().getCount() / player.getHeldItemOffhand().getMaxStackSize() <= (50 / 100.0)) {
                return 45;
            }
        }
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.inventory.mainInventory.get(i);
            if (stack.getItem() != Items.AIR && stack.getCount() < stack.getMaxStackSize() && (double) stack.getCount() / stack.getMaxStackSize() <= (50 / 100.0)) {
                return i;
            }
        }
        return -1;
    }

    private int getSmallestStack(EntityPlayerSP player, ItemStack itemStack) {
        if (itemStack == null) {
            return -1;
        }
        int minCount = itemStack.getMaxStackSize() + 1;
        int minIndex = -1;

        for (int i = 9; i < player.inventory.mainInventory.size(); i++) {
            ItemStack stack = player.inventory.mainInventory.get(i);
            if (stack.getItem() != Items.AIR && stack.getItem() == itemStack.getItem() && stack.getCount() < minCount) {
                minCount = stack.getCount();
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    public void refill(Minecraft mc, int slot) {
        ItemStack stack;
        if (slot == 45) { 
            stack = mc.player.getHeldItemOffhand();
        }else {
            stack = mc.player.inventory.mainInventory.get(slot);
        }

        if (stack.getItem() == Items.AIR) {
            return;
        }

        int biggestStack = getSmallestStack(mc.player, stack);
        if (biggestStack == -1) {
            return;
        }

        if (slot == 45) {
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, biggestStack, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, biggestStack, 0, ClickType.PICKUP, mc.player);
            return;
        }

        int overflow = -1; 
        for (int i = 0; i < 9 && overflow == -1; i++) {
            if (mc.player.inventory.mainInventory.get(i).getItem() == Items.AIR) {
                overflow = i;
            }
        }
        mc.playerController.windowClick(mc.player.inventoryContainer.windowId, biggestStack, 0, ClickType.QUICK_MOVE, mc.player);

        if (overflow != -1 && mc.player.inventory.mainInventory.get(overflow).getItem() != Items.AIR) {
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, biggestStack, overflow, ClickType.SWAP, mc.player);
        }
    }
}
