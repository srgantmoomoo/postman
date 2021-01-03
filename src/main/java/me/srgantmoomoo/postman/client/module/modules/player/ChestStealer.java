package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.api.util.misc.Timer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;

public class ChestStealer extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "steal", "steal", "drop");
	public NumberSetting delay = new NumberSetting("delay", this, 1, 0, 10, 1);
	
	public ChestStealer() {
		super ("chestStealer", "slows down ur hungerness", Keyboard.KEY_NONE, Category.EXPLOITS);
		this.addSettings(mode,delay);
	}

	private Timer timer = new Timer();

    @EventHandler
    private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(p_Event ->
    {
        if (!timer.passed(delay.getValue() * 100f))
            return;

        timer.reset();

        if((Module.mc.player.openContainer != null) && ((Module.mc.player.openContainer instanceof ContainerChest))) {
        	ContainerChest chest = (ContainerChest) Module.mc.player.openContainer;
                
                for(int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); ++i) {
    				if((chest.getLowerChestInventory().getStackInSlot(i) != null)) {
    					if(mode.getMode().equals("steal")) {
    						  Module.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.QUICK_MOVE, Module.mc.player);
    					if(mode.getMode().equals("drop")) {  
    						Module.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.THROW, Module.mc.player);
    					}
                }
            }
                }
        }
    });
}