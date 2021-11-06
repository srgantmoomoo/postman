package me.srgantmoomoo.mixin.mixins;

import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.movement.NoSlow;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockWeb.class)
public class MixinBlockWeb {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn, CallbackInfo info) {
		if (ModuleManager.isModuleEnabled("noSlow") && ((NoSlow)ModuleManager.getModuleByName("noSlow")).web.isEnabled())
        	info.cancel();
    }
}