package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.event.events.PlayerUpdateEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.api.util.world.JTimer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;

public class ChestStealer extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "steal", "steal", "drop");
	public NumberSetting delay = new NumberSetting("delay", this, 1, 0, 10, 1);
	
	public ChestStealer() {
		super ("chestStealer", "slows down ur hungerness", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode,delay);
	}

	private JTimer timer = new JTimer();
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	

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