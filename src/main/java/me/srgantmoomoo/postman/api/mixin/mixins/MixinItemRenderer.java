package me.srgantmoomoo.postman.api.mixin.mixins;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.postman.api.event.events.TransformSideFirstPersonEvent;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.render.ViewModel;

/** Check ViewModel.class for further credits **/

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

	@Inject(method = "transformSideFirstPerson", at = @At("HEAD"))
	public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo callbackInfo) {
		TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
		Main.EVENT_BUS.post(event);
	}

	@Inject(method = "transformFirstPerson", at = @At("HEAD"))
	public void transformFirstPerson(EnumHandSide hand, float p_187453_2_, CallbackInfo callbackInfo) {
		TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
		Main.EVENT_BUS.post(event);
	}

}