package me.srgantmoomoo.postman.client.module.modules.render;

import me.srgantmoomoo.postman.api.event.events.TransformSideFirstPersonEvent;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import org.lwjgl.input.Keyboard;

public class ViewModel extends Module {
	public BooleanSetting cancelEating = new BooleanSetting("noEat", this, false);
	public final NumberSetting LeftX = new NumberSetting("LeftX", this, 0, -2, 2, 0.1);
	public final NumberSetting LeftY = new NumberSetting("LeftY", this, 0, -2, 2, 0.1);
	public final NumberSetting LeftZ = new NumberSetting("LeftZ", this, 0, -2, 2, 0.1);
	public final NumberSetting RightX = new NumberSetting("RightX", this, 0, -2, 2, 0.1);
	public final NumberSetting RightY = new NumberSetting("RightY", this, 0, -2, 2, 0.1);
	public final NumberSetting RightZ = new NumberSetting("RightZ", this, 0, -2, 2, 0.1);
	
	public ViewModel() {
		super("viewModel", "allows u to change how ur model look in 1st person.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(LeftX, LeftY, LeftZ, RightX, RightY, RightZ);
	}

	@EventHandler
	private final Listener<TransformSideFirstPersonEvent> listener = new Listener<>(event -> {
		if (event.getEnumHandSide() == EnumHandSide.RIGHT) {
			GlStateManager.translate(RightX.getValue(), RightY.getValue(), RightZ.getValue());
		} else if (event.getEnumHandSide() == EnumHandSide.LEFT) {
			GlStateManager.translate(LeftX.getValue(), LeftY.getValue(), LeftZ.getValue());
		}
	});
}