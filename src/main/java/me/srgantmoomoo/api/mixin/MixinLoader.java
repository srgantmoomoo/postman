package me.srgantmoomoo.api.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import me.srgantmoomoo.postman.Main;

import javax.annotation.Nullable;
import java.util.Map;
public class MixinLoader implements IFMLLoadingPlugin{

	private static boolean isObfuscatedEnvironment = false;

	public MixinLoader(){
		Main.log.info("mixins initialized");
		MixinBootstrap.init();
		Mixins.addConfiguration("mixins.postman.json");
		MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
		Main.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
	}

	@Override
	public String[] getASMTransformerClass(){
		return new String[0];
	}

	@Override
	public String getModContainerClass(){
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass(){
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data){
		isObfuscatedEnvironment = (boolean) (Boolean) data.get("runtimeDeobfuscationEnabled");
	}

	@Override
	public String getAccessTransformerClass(){
		return null;
	}
}

