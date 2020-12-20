package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.event.events.TransformSideFirstPersonEvent;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.BooleanSetting;
import me.srgantmoomoo.postman.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;

public class ViewModel extends Module {
	public BooleanSetting cancelEating = new BooleanSetting("noEat", this, false);
	public NumberSetting LeftX = new NumberSetting("LeftX", this, 0, -2, 2, 0.1);
	public NumberSetting LeftY = new NumberSetting("LeftY", this, 0, -2, 2, 0.1);
	public NumberSetting LeftZ = new NumberSetting("LeftZ", this, 0, -2, 2, 0.1);
	public NumberSetting RightX = new NumberSetting("RightX", this, 0, -2, 2, 0.1);
	public NumberSetting RightY = new NumberSetting("RightY", this, 0, -2, 2, 0.1);
	public NumberSetting RightZ = new NumberSetting("RightZ", this, 0, -2, 2, 0.1);
	
	public ViewModel() {
		super("viewModel", "classic hud", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(LeftX, LeftY, LeftZ, RightX, RightY, RightZ);
	}

	@EventHandler
	private final Listener<TransformSideFirstPersonEvent> eventListener = new Listener<>(event -> {
		if (event.getHandSide() == EnumHandSide.RIGHT){
			GlStateManager.translate(RightX.getValue(), RightY.getValue(), RightZ.getValue());
		} else if (event.getHandSide() == EnumHandSide.LEFT){
			GlStateManager.translate(LeftX.getValue(), LeftY.getValue(), LeftZ.getValue());
		}
	});

	public void onEnable(){
		Main.EVENT_BUS.subscribe(this);
	}

	public void onDisable(){
		Main.EVENT_BUS.unsubscribe(this);
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}
