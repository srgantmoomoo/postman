package me.srgantmoomoo.postman.impl.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleDisableEvent;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleEnableEvent;
import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

/**
 * @author SrgantMooMoo
 * @since 4/2/22
 */

public class NotificationModule extends Module {
    public static NotificationModule INSTANCE;
    public ModeSetting mode = new ModeSetting("mode", this, "chat", "chat", "hud");
    public BooleanSetting toggles = new BooleanSetting("moduleToggles", this, true);

    @EventHandler
    private final Listener<PostmanModuleEnableEvent> moduleEnableEvent = new Listener<>(event -> {
        if (!toggles.isEnabled())
            return;
        if (event.mod.getName().equalsIgnoreCase("clickGui"))
            return;
        sendNotification(event.mod.getName() + ChatFormatting.GREEN + " enabled" + ChatFormatting.GRAY + ".");
    });

    @EventHandler
    private final Listener<PostmanModuleDisableEvent> moduleDisableEvent = new Listener<>(event -> {
        if (!toggles.isEnabled())
            return;
        if (event.mod.getName().equalsIgnoreCase("clickGui"))
            return;
        sendNotification(event.mod.getName() + ChatFormatting.RED + " disabled" + ChatFormatting.GRAY + ".");
    });

    public BooleanSetting coordsOnDeath = new BooleanSetting("coordsOnDeath", this, false);
    boolean run = true;

    public NotificationModule() {
        super("notification", "send notifications.", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(mode, toggles, coordsOnDeath);
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (coordsOnDeath.isEnabled()) {
            if (mc.player.isDead) {
                if (run)
                    sendNotification(ChatFormatting.WHITE + "lol u just died loser" + ChatFormatting.GRAY + " (x)" + mc.player.getPosition().x + " (y)" + mc.player.getPosition().y + " (z)" + mc.player.getPosition().z);
                run = false;
            }
            if (!mc.player.isDead) {
                run = true;
            }
        }
    }

    public void sendNotification(String message) {
        if (this.isToggled()) {
            if (mode.is("chat"))
                Main.INSTANCE.commandManager.sendClientChatMessage(message, true);
            else
                System.out.println("this is a hud message");
        }
    }
}
