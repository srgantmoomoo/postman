package me.srgantmoomoo.postman.impl.modules.client;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

/**
 * @author SrgantMooMoo
 * @since 4/1/22
 */

public class NotificationModule extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "chat", "chat", "hud");
    public static NotificationModule INSTANCE;

    public NotificationModule() {
        super("notification", "send notifications", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(mode);
        INSTANCE = this;
    }

    public void sendNoti(String message) {
        if(mode.is("chat"))
            Main.INSTANCE.commandManager.sendClientChatMessage(message, true);
        else
            System.out.println("this is a hud message");
    }

}
