package me.srgantmoomoo.postman.module.modules.player;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Event;
import me.srgantmoomoo.postman.event.events.EventTick;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
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
        if(mc.currentScreen instanceof ChatScreen || mc.currentScreen instanceof SignEditScreen || mc.currentScreen instanceof AnvilScreen) return;

        long handle = mc.getWindow().getHandle();
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_UP))
            mc.player.setPitch(Math.max(mc.player.getPitch() - 5, -90));
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_DOWN))
            mc.player.setPitch(Math.min(mc.player.getPitch() + 5, 90));
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT))
            mc.player.setYaw(mc.player.getYaw() + 5);
        if(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT))
            mc.player.setYaw(mc.player.getYaw() - 5);

        mc.options.forwardKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_W) ||
                Main.INSTANCE.moduleManager.getModuleByName("autoWalk").isModuleEnabled());
        mc.options.backKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_S));
        mc.options.leftKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_A));
        mc.options.rightKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_D));
        mc.options.jumpKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_SPACE));
        mc.options.sneakKey.setPressed(InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_SHIFT));
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.options.forwardKey.setPressed(false);
        mc.options.backKey.setPressed(false);
        mc.options.leftKey.setPressed(false);
        mc.options.rightKey.setPressed(false);
        mc.options.jumpKey.setPressed(false);
        mc.options.sneakKey.setPressed(false);
    }
}
