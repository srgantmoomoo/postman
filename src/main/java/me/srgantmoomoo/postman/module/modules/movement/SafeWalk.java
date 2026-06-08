package me.srgantmoomoo.postman.module.modules.movement;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;

public class SafeWalk extends Module {
    public SafeWalk() {
        super("safeWalk", "prevents falling off blocks.", Category.MOVEMENT, 0);
    }
    // handled in MixinClientPlayerEntity
}
