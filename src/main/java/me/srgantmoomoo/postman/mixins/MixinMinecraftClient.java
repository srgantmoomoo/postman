package me.srgantmoomoo.postman.mixins;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.modules.player.AutoReconnect;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;Z)V", at = @At("TAIL"))
    private void onDisconnect(Screen screen, boolean transferring, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        Module autoReconnect = Main.INSTANCE.moduleManager.getModuleByName("autoReconnect");

        if(autoReconnect == null || !autoReconnect.isModuleEnabled()) return;
        if(AutoReconnect.lastIp == null || AutoReconnect.lastPort <= 0) return;
        if(transferring) return;

        int delaySeconds = (int) ((NumberSetting) autoReconnect.getSettingByName("delay")).getValue();

        new Thread(() -> {
            try {
                Thread.sleep(delaySeconds * 1000L);
                mc.execute(() -> {
                    ConnectScreen.connect(new TitleScreen(), mc,
                            new ServerAddress(AutoReconnect.lastIp, AutoReconnect.lastPort),
                            new ServerInfo("", AutoReconnect.lastIp, ServerInfo.ServerType.OTHER),
                            false, null);
                });
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
