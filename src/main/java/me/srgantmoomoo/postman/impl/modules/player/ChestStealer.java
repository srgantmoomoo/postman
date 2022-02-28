package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.backend.util.world.JTimer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;

public class ChestStealer extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "steal", "steal", "drop");
	public NumberSetting delay = new NumberSetting("delay", this, 1, 0, 10, 1);
	
	public ChestStealer() {
		super ("chestStealer", "automatically steals from inventory gui's.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode,delay);
	}

	private JTimer timer = new JTimer();
	

    @EventHandler
    private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(event -> {

        if((Module.mc.player.openContainer != null) && ((Module.mc.player.openContainer instanceof ContainerChest))) {
        	ContainerChest chest = (ContainerChest) Module.mc.player.openContainer;
                
                for(int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++) {
    				if((chest.getLowerChestInventory().getStackInSlot(i) != null) && (this.timer.hasReached(40L))) {
    					if(mode.is("steal")) {
    						Module.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.QUICK_MOVE, Module.mc.player);
    						this.timer.reset();
	    					if(mode.is("drop")) {  
	    						Module.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.THROW, Module.mc.player);
	    						this.timer.reset();
	    					}
    					}
    				}
                }
        }
    });
}