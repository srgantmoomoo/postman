package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.SlotActionType;

public class ChestStealer extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "steal", "steal", "drop");
    public NumberSetting delay = new NumberSetting("delay", this, 1, 0, 10, 1);
    private long lastAction = 0;

    public ChestStealer() {
        super("chestStealer", "automatically steals everything from opened container gui's.", Category.PLAYER, 0);
        this.addSettings(mode, delay);
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null || mc.currentScreen == null) return;
        if(!(mc.currentScreen instanceof GenericContainerScreen screen)) return;
        if(System.currentTimeMillis() - lastAction < delay.getValue() * 100L) return;

        Inventory inv = screen.getScreenHandler().getInventory();
        for(int i = 0; i < inv.size(); i++) {
            if(!inv.getStack(i).isEmpty()) {
                if(mode.is("steal")) {
                    mc.interactionManager.clickSlot(screen.getScreenHandler().syncId, i, 0, SlotActionType.QUICK_MOVE, mc.player);
                } else if(mode.is("drop")) {
                    mc.interactionManager.clickSlot(screen.getScreenHandler().syncId, i, 1, SlotActionType.THROW, mc.player);
                }
                lastAction = System.currentTimeMillis();
                return;
            }
        }
    }
}
