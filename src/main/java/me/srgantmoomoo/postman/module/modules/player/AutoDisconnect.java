package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;

public class AutoDisconnect extends Module {
    public NumberSetting health = new NumberSetting("health", this, 10, 1, 30, 1);

    public AutoDisconnect() {
        super ("autoDisconnect", "automatically disconnects at desired health.", Category.PLAYER, 0);
        this.addSettings(health);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.world == null) return;

            if(mc.player.getHealth() <= health.getValue()) {
                this.disable();
                mc.disconnect();
                //mc.setScreen(new MultiplayerScreen());
            }
        }
    }
}
