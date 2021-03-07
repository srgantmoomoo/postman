package me.srgantmoomoo.postman.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class Donate extends Module {
	
	public Donate() {
		super("donate", "give srgantmoomoo money he needs ur help now!.", Keyboard.KEY_NONE, Category.CLIENT);
	}
    @Override
    public void onEnable()
    {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://www.buymeacoffee.com/srgantmoomoo"));
            }
        } catch (Exception e) {e.printStackTrace();}
        ModuleManager.Get().GetMod(DonateModule.class).setEnabled(false);
    }

}
