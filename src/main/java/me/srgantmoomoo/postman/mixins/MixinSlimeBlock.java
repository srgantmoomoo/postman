package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.modules.movement.NoSlow;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlimeBlock.class)
public class MixinSlimeBlock {

    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void noSlowSlime(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo info) {
        if(!(entity instanceof ClientPlayerEntity)) return;
        NoSlow noSlow = (NoSlow) Main.INSTANCE.moduleManager.getModuleByName("noSlow");
        if(noSlow != null && noSlow.isModuleEnabled() && noSlow.slimeBlock.isEnabled()) {
            info.cancel();
        }
    }
}
