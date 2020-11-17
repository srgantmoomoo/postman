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
	public BooleanSetting cancelEating = new BooleanSetting("noEat", false);
	public NumberSetting LeftX = new NumberSetting("LeftX", 0, -2, 2, 0.1);
	public NumberSetting LeftY = new NumberSetting("LeftY", 0, -2, 2, 0.1);
	public NumberSetting LeftZ = new NumberSetting("LeftZ", 0, -2, 2, 0.1);
	public NumberSetting RightX = new NumberSetting("RightX", 0, -2, 2, 0.1);
	public NumberSetting RightY = new NumberSetting("RightY", 0, -2, 2, 0.1);
	public NumberSetting RightZ = new NumberSetting("RightZ", 0, -2, 2, 0.1);
	
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
}
