package me.srgantmoomoo.postman.impl.modules.hud;

import java.awt.Color;
import java.awt.Point;
import java.util.Comparator;
import java.util.Objects;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.backend.event.events.RenderEntityNameEvent;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.backend.util.world.EntityUtil;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.HudModule;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.srgantmoomoo.postman.impl.clickgui.back.ClickGui;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class TargetHud extends HudModule {
    public ColorSetting background = new ColorSetting("backgroundColor", this, new JColor(0, 0, 0, 150));
    public ColorSetting text = new ColorSetting("textColor", this, Reference.POSTMAN_COLOR);
    public NumberSetting range = new NumberSetting("range", this, 100, 10, 260, 1);

    private EntityPlayer targetPlayer;

    public TargetHud() {
        super("targetHud", "gives you a hud of your target opponent.", new Point(0,70), Category.HUD);
        this.addSettings(range, text, background);
    }

    public void onEnable() {
        Main.EVENT_BUS.subscribe(this);
    }

    public void onDisable() {
        Main.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<RenderEntityNameEvent> OnDamageBlock = new Listener<>(event -> {
        event.cancel();
    });

    @Override
    public void populate(Theme theme) {
        component = new TargetHUDComponent(theme);
    }

    private Color getNameColor(String playerName) {
        return new JColor(255, 255, 255, 255);
    }

    private boolean isValidEntity(Entity e) {
        if (!(e instanceof EntityPlayer)) return false;
        else return e!=mc.player;
    }

    private float getPing(EntityPlayer player) {
        float ping = 0;
        try {
            ping = EntityUtil.clamp(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1, 300.0f);
        }
        catch (NullPointerException ignored) {}
        return ping;
    }

    private class TargetHUDComponent extends HUDComponent {

        public TargetHUDComponent (Theme theme) {
            super(getName(), theme.getPanelRenderer(), TargetHud.this.position);
        }

        @Override
        public void render (Context context) {
            super.render(context);
            // Render content
            if (mc.world != null && mc.player.ticksExisted >= 10) {
                EntityPlayer entityPlayer = (EntityPlayer) mc.world.loadedEntityList.stream()
                        .filter(TargetHud.this::isValidEntity)
                        .map(entity -> (EntityLivingBase) entity)
                        .min(Comparator.comparing(c -> mc.player.getDistance(c)))
                        .orElse(null);
                if (entityPlayer!= null && entityPlayer.getDistance(mc.player) <= range.getValue()) {

                    // Render background
                    Color bgcolor = new JColor(background.getValue());
                    context.getInterface().fillRect(context.getRect(),bgcolor,bgcolor,bgcolor,bgcolor);

                    // Render player
                    targetPlayer = entityPlayer;
                    ClickGui.renderEntity(entityPlayer,new Point(context.getPos().x + 20, context.getPos().y + 50 - (entityPlayer.isSneaking()?10:0)), 23);
                    targetPlayer = null;

                    // Render name
                    String playerName = entityPlayer.getName();
                    Color nameColor=getNameColor(playerName);
                    context.getInterface().drawString(new Point(context.getPos().x + 40, context.getPos().y + 7), TextFormatting.ITALIC + playerName, nameColor);

                    // Render health
                    int playerHealth = (int) (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount());
                    context.getInterface().drawString(new Point(context.getPos().x + 40, context.getPos().y + 17), TextFormatting.WHITE + "health " + TextFormatting.RESET + playerHealth, playerHealth > 16 ? new JColor(0, 255, 0) : new JColor(255,0,0));

                    // Render distance
                    context.getInterface().drawString(new Point(context.getPos().x + 40, context.getPos().y + 27), TextFormatting.WHITE + "distance " + TextFormatting.RESET + ((int)entityPlayer.getDistance(mc.player)), new JColor(text.getValue(), 255));

                    // Render ping and info
                    context.getInterface().drawString(new Point(context.getPos().x + 40, context.getPos().y + 37), TextFormatting.WHITE + "ping " + TextFormatting.RESET + getPing(entityPlayer), getPing(entityPlayer) > 100 ? new JColor(255, 0, 0) : new JColor(0, 255, 0));

                    // Render items
                    int yPos=context.getPos().y + 57;
                    for (ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
                        yPos-=15;
                        ClickGui.renderItem(itemStack, new Point(context.getPos().x + 120, yPos));
                    }
                }
            }
        }

        @Override
        public int getWidth (Interface inter) {
            return 120;
        }

        @Override
        public void getHeight (Context context) {
            context.setHeight(54);
        }
    }
}
