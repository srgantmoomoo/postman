package me.srgantmoomoo.postman.module.modules.render;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FullBright extends Module {
    //TODO add gamma option

    public FullBright() {
        super("fullBright", "light up the world.", Category.RENDER, 0);
    }

    @Override

    public void onEvent(Event e) {
        if(e instanceof EventTick)
            MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 500, 0));
    }

    @Override
    public void onDisable() {
        MinecraftClient.getInstance().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }

}
