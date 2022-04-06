package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.Event.Era;
import me.srgantmoomoo.postman.backend.event.events.AddEntityEvent;
import me.srgantmoomoo.postman.backend.event.events.NetworkPacketEvent;
import me.srgantmoomoo.postman.backend.event.events.PacketEvent;
import me.srgantmoomoo.postman.backend.event.events.RenderEntityEvent;
import me.srgantmoomoo.postman.backend.event.events.RenderRainEvent;
import me.srgantmoomoo.postman.backend.event.events.SpawnEffectEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;

public class NoRender extends Module {
	public BooleanSetting rain = new BooleanSetting("rain", this, false);
	public BooleanSetting skylight = new BooleanSetting("skylightUpdates", this, false);
	public ModeSetting hurtCam = new ModeSetting("hurtCam", this, "yesHurtCam", "yesHurtCam", "noHurtCam", "penis");
	public BooleanSetting fire = new BooleanSetting("fire", this, false);
	public BooleanSetting portalEffect = new BooleanSetting("portalEffect", this, false);
	public BooleanSetting potionIndicators = new BooleanSetting("potionIndicators", this, false);
	public BooleanSetting crystals = new BooleanSetting("crystals", this, false);
	public BooleanSetting totemAnimation = new BooleanSetting("totemAnimation", this, false);
	public BooleanSetting enchantTables = new BooleanSetting("encahtTables", this, false);
	public BooleanSetting armor = new BooleanSetting("armor", this, false);
	public BooleanSetting tnt = new BooleanSetting("tnt", this, false);
	public BooleanSetting items = new BooleanSetting("items", this, false);
	public BooleanSetting withers = new BooleanSetting("withers", this, false);
	public BooleanSetting skulls = new BooleanSetting("skulls", this, false);
	public BooleanSetting fireworks = new BooleanSetting("fireworks", this, false);
	
	public BooleanSetting particles = new BooleanSetting("particles", this, false);
	public BooleanSetting signs = new BooleanSetting("signs", this, false);
	public BooleanSetting pistons = new BooleanSetting("pistons", this, false);
		
	public NoRender() {
		super("noRender", "stops certain events from rendering.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(rain, skylight, hurtCam, fire, portalEffect, potionIndicators, crystals, totemAnimation, enchantTables, armor, tnt, items, withers, skulls, fireworks);
	}

	@Override
	public void onDisable() {
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
	private final Listener<RenderRainEvent> onRain = new Listener<>(event -> {
		if(rain.isEnabled()) {
		    if (mc.world == null)
		        return;
		    event.cancel();
		}
	});
	
	// totem animation
	@EventHandler
    private final Listener<NetworkPacketEvent> PacketEvent = new Listener<>(event -> {
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
    private final Listener<RenderBlockOverlayEvent> OnBlockOverlayEvent = new Listener<>(event -> {
        if (fire.isEnabled() && event.getOverlayType() == OverlayType.FIRE) event.setCanceled(true);
    });
	
	// crystals, tnt, items, withers, skulls, and fireworks
	
	@EventHandler
	private final Listener<PacketEvent.Receive> onReceivePacket = new Listener<>(event -> {
		 if (event.getEra() == Era.PRE) {
	            if (event.getPacket() instanceof SPacketSpawnMob) {
	                final SPacketSpawnMob packet = (SPacketSpawnMob) event.getPacket();

	                if (this.skulls.isEnabled()) {
	                    if (packet.getEntityType() == 19) {
	                        event.cancel();
	                    }
	                }
	            }
		 }
	});
	
	@EventHandler
	private final Listener<RenderEntityEvent> onRenderEntity = new Listener<>(event -> {
			if(crystals.isEnabled()) {
				if (event.getEntity() instanceof EntityEnderCrystal) event.cancel();
			}
			
			if(tnt.isEnabled()) {
				if (event.getEntity() instanceof EntityTNTPrimed) event.cancel();
			}
			
			if(items.isEnabled()) {
				if (event.getEntity() instanceof EntityItem) event.cancel();
			}
			
			if(withers.isEnabled()) {
				if (event.getEntity() instanceof EntityWither) event.cancel();
			}
			
			if(skulls.isEnabled()) {
				if (event.getEntity() instanceof EntityWitherSkull) event.cancel();
			}
			
			if(fireworks.isEnabled()) {
				if (event.getEntity() instanceof EntityFireworkRocket) event.cancel();
			}		
        
	});
	@EventHandler
	private final Listener<SpawnEffectEvent> onSpawnEffectParticle = new Listener<>(event -> {
		if (fireworks.isEnabled()) {
            if (event.getParticleID() == EnumParticleTypes.FIREWORKS_SPARK.getParticleID() || event.getParticleID() == EnumParticleTypes.EXPLOSION_HUGE.getParticleID() ||
            		event.getParticleID() == EnumParticleTypes.EXPLOSION_LARGE.getParticleID() || event.getParticleID() == EnumParticleTypes.EXPLOSION_NORMAL.getParticleID()) {
                event.cancel();
            }
        }
	});
	
	@EventHandler
	private final Listener<AddEntityEvent> onEntityAdd = new Listener<>(event -> {
		if (fireworks.isEnabled()) {
            if (event.getEntity() instanceof EntityFireworkRocket) {
                event.cancel();
            }
        }

        if (skulls.isEnabled()) {
            if (event.getEntity() instanceof EntityWitherSkull) {
                event.cancel();
            }
        }

        if (tnt.isEnabled()) {
            if (event.getEntity() instanceof EntityTNTPrimed) {
                event.cancel();
            }
        }
	});
	
	// hurtCam = MixinEntityRenderer
	// potionEffect = mixin... some sorta overlay idk
	// skylight = MixinWorld
	// armor = MixinLayerBipedArmor
	
}
