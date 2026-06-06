package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "sickomode");

    public Sprint() {
        super("sprint", "now u cant walk, good going.", Category.MOVEMENT, 0);
        this.addSettings(mode);
    }

    @Override
    public void onEvent(Event e) {
        if (!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (mode.is("normal")) {
            if (mc.options.forwardKey.isPressed() && !mc.player.isSneaking() && !mc.player.horizontalCollision) {
                mc.player.setSprinting(true);
            }
        } else if (mode.is("sickomode")) {
            mc.player.setSprinting(true);
        }
    }
}
