package me.srgantmoomoo.postman.impl.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.NetworkPacketEvent;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleDisableEvent;
import me.srgantmoomoo.postman.backend.event.events.postman.PostmanModuleEnableEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import org.lwjgl.input.Keyboard;

/**
 * @author SrgantMooMoo
 * @since 4/2/22
 */

public class NotificationModule extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "chat", "chat", "hud");
    public BooleanSetting toggles = new BooleanSetting("moduleToggles", this, true);
    public static NotificationModule INSTANCE;

    public NotificationModule() {
        super("notification", "send notifications.", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(mode, toggles);
        INSTANCE = this;
    }

    @EventHandler
    private final Listener<PostmanModuleEnableEvent> moduleEnableEvent = new Listener<>(event -> {
        if(!toggles.isEnabled())
            return;
        if(event.mod.getName().equalsIgnoreCase("clickGui"))
            return;
        sendNoti(event.mod.getName() + ChatFormatting.GREEN + " enabled" + ChatFormatting.GRAY + ".");
    });

    @EventHandler
    private final Listener<PostmanModuleDisableEvent> moduleDisableEvent = new Listener<>(event -> {
        if(!toggles.isEnabled())
            return;
        if(event.mod.getName().equalsIgnoreCase("clickGui"))
            return;
        sendNoti(event.mod.getName() + ChatFormatting.DARK_RED + " disabled" + ChatFormatting.GRAY + ".");
    });

    public void sendNoti(String message) {
        if(this.isToggled()) {
            if (mode.is("chat"))
                Main.INSTANCE.commandManager.sendClientChatMessage(message, true);
            else
                System.out.println("this is a hud message");
        }
    }
}
