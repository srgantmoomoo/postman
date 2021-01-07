package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.api.util.render.JColor;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.module.ModuleManager;
import me.srgantmoomoo.postman.client.module.modules.pvp.AutoCrystal;
import me.srgantmoomoo.postman.client.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;


public class Info extends HudModule {
	private InfoList list=new InfoList();
	 
	public ColorSetting color1 = new ColorSetting("color1", this, new JColor(103, 167, 221, 255)); 
	public ColorSetting color2 = new ColorSetting("color2", this, new JColor(255, 0, 0, 255)); 
	
	public Info() {
		super("info", "thatweehoo", new Point(-3,10));
		this.addSettings(color1, color2);
	}
    @Override
    public void populate (Theme theme) {
    	component = new ListComponent(getName(),theme.getPanelRenderer(),position,list);
    }

    public void onRender() {
    	list.totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
    	list.players = mc.world.loadedEntityList.stream()
                .filter(entity -> entity instanceof EntityOtherPlayerMP)
                .map(entity -> (EntityOtherPlayerMP) entity)
                .min(Comparator.comparing(cl -> mc.player.getDistance(cl)))
                .orElse(null);
    	list.renderLby=false;
    	AutoCrystal a = (AutoCrystal) ModuleManager.getModuleByName("autoCrystal");
    }

    private static int getPing () {
        int p = -1;
        if (mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
            p = -1;
        }
        else {
            p = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
        }
        return p;
    }

    private class InfoList implements HUDList {

		public int totems=0;
		public EntityOtherPlayerMP players=null;
		public boolean renderLby=false;
		public boolean lby=false;
		
		@Override
		public int getSize() {
			return totems;
		}
	
		@Override
		public String getItem(int index) {
				if (index==0) return "postman";
				else if (index==1) return ""+totems;
				else if (index==2) return "PING "+getPing();
				else return "LBY";
		}
	
		@Override
		public Color getItemColor(int index) {
				boolean on=false;
				if (index==0) {
					on=true;
				}
				else if (index==1) {
					if (players!=null) {
					}
				}
				else if (index==2) {
					if (players!=null) {
					}
				}
				else if (index==3) {
					on=totems>0 && ModuleManager.isModuleEnabled("AutoTotem");
				}
				else if (index==4) {
					on=getPing()<=100;
				}
				else {
					on=lby;
				}
				if (on) return color1.getValue();
				else return color2.getValue();
		}

		@Override
		public boolean sortUp() {
			return false;
		}

		@Override
		public boolean sortRight() {
			return false;
		}
	}
}