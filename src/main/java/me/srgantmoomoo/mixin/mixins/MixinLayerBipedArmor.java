package me.srgantmoomoo.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.impl.modules.render.NoRender;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LayerBipedArmor.class)
public class MixinLayerBipedArmor {

    @Inject(method = "setModelSlotVisible", at = @At(value = "HEAD"), cancellable = true)
    protected void setModelSlotVisible(ModelBiped model, EntityEquipmentSlot slot, CallbackInfo callbackInfo) {
        if (NoRender.INSTANCE.isToggled() && NoRender.INSTANCE.armor.isEnabled()) {
            callbackInfo.cancel();
            switch (slot) {
                case HEAD: {
                    model.bipedHead.showModel = false;
                    model.bipedHeadwear.showModel = false;
                }
                case CHEST: {
                    model.bipedBody.showModel = false;
                    model.bipedRightArm.showModel = false;
                    model.bipedLeftArm.showModel = false;
                }
                case LEGS: {
                    model.bipedBody.showModel = false;
                    model.bipedRightLeg.showModel = false;
                    model.bipedLeftLeg.showModel = false;
                }
                case FEET: {
                    model.bipedRightLeg.showModel = false;
                    model.bipedLeftLeg.showModel = false;
                }
            }
        }
    }
}