package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.renderer.ItemRenderer;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

	public class LowOffHand extends Module {
		public NumberSetting lowness = new NumberSetting("lowness", this, 0.7, 0, 1, 0.1);
		
		public LowOffHand() {
			super ("lowOffHand", "lowers the offhand.", Keyboard.KEY_NONE, Category.RENDER);
			this.addSettings(lowness);
		}
		
		ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;
		
		@Override
		public void onUpdate(){
			itemRenderer.equippedProgressOffHand = (float) lowness.getValue();
		}
	
}


