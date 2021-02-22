package me.srgantmoomoo.postman.api.mixin.mixins.accessor;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/*
 * this is from gamesense, im just a gamesense skid don't talk to me.
 */

@Mixin(EntityPlayerSP.class)
public interface AccessorEntityPlayerSP {

    @Accessor("handActive")
    void gsSetHandActive(boolean value);

}