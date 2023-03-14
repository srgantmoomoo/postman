package me.srgantmoomoo.postman.module.modules.render;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class VibrantShader extends Module {

    public VibrantShader() {
        super("vibrantShader", "applies a vibrant shader to minecraft :o.", Category.RENDER, 0);
    }

    /*ManagedShaderEffect shader = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/color_convolve" + ".json"));
    public void onEvent(Event e) {
        if(e instanceof EventRender3d) {

        }
    }*/

}
