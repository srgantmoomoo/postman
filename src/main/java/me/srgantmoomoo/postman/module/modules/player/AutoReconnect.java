package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.module.setting.settings.NumberSetting;

public class AutoReconnect extends Module {
    public NumberSetting delay = new NumberSetting("delay", this, 5, 1, 20, 1);

    public static boolean isReconnecting = false;
    public static String lastIp;
    public static int lastPort;

    public AutoReconnect() {
        super("autoReconnect", "automatically reconnects to a server.", Category.PLAYER, 0);
        this.addSettings(delay);
    }
    // ip and port saved in MixinConnectScreen
    // connection done in MixinMinecraftClient
}
