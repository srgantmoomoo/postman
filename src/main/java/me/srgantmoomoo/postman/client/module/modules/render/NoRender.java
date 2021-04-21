package me.srgantmoomoo.postman.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.NetworkPacketEvent;
import me.srgantmoomoo.postman.api.event.events.RenderRainEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.common.MinecraftForge;

public class NoRender extends Module {
	public BooleanSetting rain = new BooleanSetting("rain", this, false);
	public ModeSetting hurtCam = new ModeSetting("hurtCam", this, "false", "false", "normal", "penis");
	public BooleanSetting potionEffects = new BooleanSetting("potionEffects", this, false);
	public BooleanSetting fire = new BooleanSetting("fire", this, false);
	public BooleanSetting portalEffect = new BooleanSetting("portalEffect", this, false);
	public BooleanSetting enchantTables = new BooleanSetting("encahtTables", this, false);
	public BooleanSetting crystalEffects = new BooleanSetting("crystalEffects", this, false);
	public BooleanSetting crystals = new BooleanSetting("crystals", this, false);
	public BooleanSetting totemAnimation = new BooleanSetting("totemAnimation", this, false);
	public BooleanSetting armor = new BooleanSetting("armor", this, false);
	public BooleanSetting skylight = new BooleanSetting("skylightUpdates", this, false);
	
	public NoRender() {
		super("noRender", "stops certain events from rendering.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(hurtCam, rain, fire, potionEffects, portalEffect, enchantTables, crystalEffects, crystals, totemAnimation, armor, skylight);
	}
	
	@Override
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		MinecraftForge.EVENT_BUS.unregister(this);
		GuiIngameForge.renderPortal = true;
	}
	
	@Override
	public void onUpdate() {
		// hurtCam penis mode
		if(hurtCam.is("penis")) {
			mc.player.performHurtAnimation();
		}
		
		// portalEffect
		if(portalEffect.isEnabled()) {
			GuiIngameForge.renderPortal = false;
			mc.player.removeActivePotionEffect(MobEffects.NAUSEA);
		}
	}
	
	// rain
	@EventHandler
	private Listener<RenderRainEvent> onRain = new Listener<>(event -> {
		if(rain.isEnabled()) {
		    if (mc.world == null)
		        return;
		    event.cancel();
		}
	});
	
	// totem animation
	@EventHandler
    private Listener<NetworkPacketEvent> PacketEvent = new Listener<>(event -> {
        if (mc.world == null || mc.player == null) return;
        if (event.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35) {
                if (totemAnimation.isEnabled())
                    event.cancel();
            }
        }
    });
	
	// fire
	@EventHandler
    private Listener<RenderBlockOverlayEvent> OnBlockOverlayEvent = new Listener<>(event -> {
        if (fire.isEnabled() && event.getOverlayType() == OverlayType.FIRE) event.setCanceled(true);
    });
	
	// hurtCam = MixinEntityRenderer
	// potionEffect = mixin... some sorta overlay idk
	// skylight = MixinWorld
	// armor = MixinLayerBipedArmor
	
}
