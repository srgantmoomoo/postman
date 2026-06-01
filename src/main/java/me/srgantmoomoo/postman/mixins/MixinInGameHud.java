package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.clickgui.HudEditorScreen;
import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventRender2d;
import me.srgantmoomoo.postman.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo info) {
        // render hud modules to in game hud
        if(!(MinecraftClient.getInstance().currentScreen instanceof HudEditorScreen))
            Main.INSTANCE.hudManager.renderMods(context);

        EventRender2d e = new EventRender2d(context);
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}