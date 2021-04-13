package me.srgantmoomoo.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import me.srgantmoomoo.Main;

import javax.annotation.Nullable;
import java.util.Map;
public class MixinLoader implements IFMLLoadingPlugin {

	public MixinLoader(){
		Main.log.info("mixins initialized");
		MixinBootstrap.init();
		Mixins.addConfigurations("mixins.postman.json", "mixins.baritone.json");
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}