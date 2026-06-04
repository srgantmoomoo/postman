package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class Refill extends Module {
    public NumberSetting delay = new NumberSetting("delay", this, 500, 0, 2000, 1);
    private long lastRefill = 0;

    public Refill() {
        super("refill", "automatically refills stacks in ur hotbar and offHand.", Category.PLAYER, 0);
        this.addSettings(delay);
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null) return;
        if(mc.currentScreen instanceof InventoryScreen) return;
        if(System.currentTimeMillis() - lastRefill < delay.getValue()) return;

        PlayerInventory inv = mc.player.getInventory();

        ItemStack offhand = mc.player.getOffHandStack();
        if(!offhand.isEmpty() && offhand.getCount() < offhand.getMaxCount()) {
            int source = findMatchingStack(mc, offhand);
            if(source != -1) {
                int syncId = mc.player.playerScreenHandler.syncId;
                mc.interactionManager.clickSlot(syncId, source, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(syncId, source, 0, SlotActionType.PICKUP, mc.player);
                lastRefill = System.currentTimeMillis();
                return;
            }
        }

        for(int i = 0; i < 9; i++) {
            ItemStack stack = inv.getStack(i);
            if(!stack.isEmpty() && stack.getCount() < stack.getMaxCount()) {
                int source = findMatchingStack(mc, stack);
                if(source != -1) {
                    int syncId = mc.player.playerScreenHandler.syncId;
                    mc.interactionManager.clickSlot(syncId, source, 0, SlotActionType.PICKUP, mc.player);
                    mc.interactionManager.clickSlot(syncId, i + 36, 0, SlotActionType.PICKUP, mc.player);
                    mc.interactionManager.clickSlot(syncId, source, 0, SlotActionType.PICKUP, mc.player);
                    lastRefill = System.currentTimeMillis();
                    return;
                }
            }
        }
    }

    private int findMatchingStack(MinecraftClient mc, ItemStack target) {
        PlayerInventory inv = mc.player.getInventory();
        int maxCount = 0;
        int maxIndex = -1;
        for(int i = 9; i < 36; i++) {
            ItemStack stack = inv.getStack(i);
            if(!stack.isEmpty() && stack.getItem() == target.getItem() && stack.getCount() > maxCount) {
                maxCount = stack.getCount();
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
