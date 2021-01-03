package me.srgantmoomoo.postman.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoDisconnect extends Module {
	public NumberSetting health = new NumberSetting("delay", this, 10, 1, 30, 1);

	
	public AutoDisconnect() {
		super ("autoDisconnect", "seeeeeee", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(health);
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
    	if(toggled) {
        if (mc.player == null || mc.world == null) return;

        if (mc.player.getHealth() <= health.getValue()) {
            disable();
            mc.world.sendQuittingDisconnectingPacket();
            mc.loadWorld(null);
            mc.displayGuiScreen(new GuiMainMenu());
        	}
        }
    }
}

