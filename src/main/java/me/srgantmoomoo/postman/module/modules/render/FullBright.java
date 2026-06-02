package me.srgantmoomoo.postman.module.modules.render;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FullBright extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "gamma", "gamma", "nightVision");

    public FullBright() {
        super("fullBright", "light up the world.", Category.RENDER, 0);
        this.addSettings(mode);
    }

    @Override
    public void onEvent(Event e) {
        if(mode.is("nightVision")) {
            if (e instanceof EventTick)
                MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 500, 0));
        }
    }

    private double lastGamma;
    @Override
    public void onEnable() {
        if(mode.is("gamma")) {
            lastGamma = MinecraftClient.getInstance().options.getGamma().getValue();
            setGamma(16.0);
        }
    }

    @Override
    public void onDisable() {
        setGamma(lastGamma);
        MinecraftClient.getInstance().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }

    // reflections, this will break probably lol
    private void setGamma(double value) {
        try {
            SimpleOption<Double> gamma = MinecraftClient.getInstance().options.getGamma();
            java.lang.reflect.Field field = SimpleOption.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(gamma, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
