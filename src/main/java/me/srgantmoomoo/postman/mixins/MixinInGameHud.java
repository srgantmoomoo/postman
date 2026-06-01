package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.HudEditorScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        // render hud modules to in game hud
        if(!(MinecraftClient.getInstance().currentScreen instanceof HudEditorScreen))
            Main.INSTANCE.hudManager.renderMods(context);
    }
}