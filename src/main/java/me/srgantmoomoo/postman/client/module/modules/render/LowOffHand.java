package me.srgantmoomoo.postman.client.module.modules.render;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.NumberSetting;
import net.minecraft.client.renderer.ItemRenderer;
import org.lwjgl.input.Keyboard;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

	public class LowOffHand extends Module {
		public final NumberSetting lowness = new NumberSetting("lowness", this, 0.7, 0, 1, 0.1);
		
		public LowOffHand() {
			super ("lowOffHand", "lowers the offhand.", Keyboard.KEY_NONE, Category.RENDER);
			this.addSettings(lowness);
		}
		
		final ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;
		
		@Override
		public void onUpdate(){
			itemRenderer.equippedProgressOffHand = (float) lowness.getValue();
		}
	
}


