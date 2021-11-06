package me.srgantmoomoo.postman.client.module.modules.pvp;

import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.lwjgl.input.Keyboard;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class AutoCope extends Module {
	public final ModeSetting msg = new ModeSetting("msg", this, "cope&seethe", "cope&seethe", "u suck", "ez pz", "gg", "customMsg");
	public final BooleanSetting greenText = new BooleanSetting("greenText", this, true);
	
	public AutoCope() {
		super("autoCope", "automatically makes ur opponent cope.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(msg, greenText);
	}
	int delay = 0;
    private static final ConcurrentHashMap<Object, Integer> targetedPlayers = new ConcurrentHashMap <>();

    public static String customMsgArg = "";
    public static void setMessage(String msg) {
    	customMsgArg = msg;
    }

    @Override
    public void onUpdate() {

        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
            	EntityPlayer player = (EntityPlayer) entity;
                if (player.getHealth() <= 0) {
                    if (targetedPlayers.containsKey(player.getName())) {
                        announce(player.getName());
                    }
                }
            }
        }

        targetedPlayers.forEach((name, timeout) -> {
            if (timeout <= 0) {
            	targetedPlayers.remove(name);
            } else {
            	targetedPlayers.put(name, timeout - 1);
            }

        });

        delay++;

    } 
    
    @EventHandler
    private final Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {

        if (mc.player == null) return;

        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity cPacketUseEntity = (CPacketUseEntity) event.getPacket();
            if (cPacketUseEntity.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                Entity targetEntity = cPacketUseEntity.getEntityFromWorld(mc.world);
                if (targetEntity instanceof EntityPlayer) {
                    addTarget(targetEntity.getName());
                }
            }
        }

    });

    @EventHandler
    private final Listener<LivingDeathEvent> livingDeathListener = new Listener<>(event -> {

        if (mc.player == null) return;

        EntityLivingBase e = event.getEntityLiving();
        if (e == null) return;

        if (e instanceof EntityPlayer) {
        	EntityPlayer player = (EntityPlayer) e;

            if (player.getHealth() <= 0) {
                if (targetedPlayers.containsKey(player.getName())) {
                    announce(player.getName());
                }
            }
        }

    });

    public void announce(String name) {
        if (delay < 150) {
            return;
        }
        delay = 0;
        targetedPlayers.remove(name);
        
        String starter = "";
        if(greenText.isEnabled()) starter = "> ";

        String message = "";
        if(msg.is("cope&seethe")) message = starter + "cope and seethe, heres a tutorial for u https://www.youtube.com/watch?v=4t5AKrZu_KE";
        if(msg.is("u suck")) message = starter + "wowowow u suck, postman owns u now.";
        if(msg.is("ez pz")) message = starter + "ez pz";
        if(msg.is("gg")) message = starter + "gg";
        if(msg.is("customMsg")) message = starter + customMsgArg;
        
        mc.player.connection.sendPacket(new CPacketChatMessage(message));
    }

    public static void addTarget(String name) {
        if (!Objects.equals(name, mc.player.getName())) {
            targetedPlayers.put(name, 20);
        }
    }
	
}
