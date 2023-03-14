package me.srgantmoomoo.postman.module.modules.render;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventRender3d;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.util.Identifier;

public class VibrantShader extends Module {

    public VibrantShader() {
        super("vibrantShader", "applies a vibrant shader to minecraft :o.", Category.RENDER, 0);
    }

    ManagedShaderEffect shader = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/color_convolve" + ".json"));
    public void onEvent(Event e) {
        if(e instanceof EventRender3d) {
            shader.render(1);
        }
    }

}
