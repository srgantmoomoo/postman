package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class GuiMove extends Module {
    public GuiMove() {
        super("guiMove", "lets you move while in ur a gui screen.", Category.PLAYER, 0);
    }

    @Override
    public void onEvent(Event e) {
        if(!(e instanceof EventTick)) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player == null || mc.currentScreen == null) return;
        if(mc.currentScreen instanceof ChatScreen) return;

        long handle = mc.getWindow().getHandle();
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_UP))
            mc.player.setPitch(Math.max(mc.player.getPitch() - 5, -90));
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_DOWN))
            mc.player.setPitch(Math.min(mc.player.getPitch() + 5, 90));
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT))
            mc.player.setYaw(mc.player.getYaw() + 5);
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT))
            mc.player.setYaw(mc.player.getYaw() - 5);
    }
}
