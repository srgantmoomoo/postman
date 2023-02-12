package me.srgantmoomoo.postman.module.modules;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import org.lwjgl.glfw.GLFW;

public class Example extends Module {

    public Example() {
        super("example", "kms.", Category.CLIENT, GLFW.GLFW_KEY_Y);
    }

    @Override
    public void onEnable() {
        System.out.println("hiiiiii.");
    }

    @Override
    public void onDisable() {
        System.out.println("byeeeee.");
    }

}
