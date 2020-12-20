package me.srgantmoomoo.postman.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

	public class LowOffHand extends Module {
		public NumberSetting lowness = new NumberSetting("lowness", this, 0, 0, 1, 0.1);
		
		public LowOffHand() {
			super ("lowOffHand", "lowers offhand", Keyboard.KEY_NONE, Category.RENDER);
			this.addSettings(lowness);
		}
		
		ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;

		
		@Override
		public void onUpdate(){
			itemRenderer.equippedProgressOffHand = (float) lowness.getValue();
		}


		@Override
		public boolean isOn() {
			// TODO Auto-generated method stub
			return false;
		}
	
}


