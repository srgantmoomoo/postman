package me.srgantmoomoo.postman.client.modules.render;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.backend.util.Wrapper;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.backend.util.render.JTessellator;
import me.srgantmoomoo.postman.backend.util.world.GeometryMasks;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

/*
 * Originally written by someone at gamesense.
 * modified by @SrgantMooMoo on 11/17/20.
 * rewrote colors on 01/24/2021.
 */

public class HoleEsp extends Module {
	public NumberSetting size = new NumberSetting("size", this, 0.1, 0.0, 1.0, 0.1);
	public BooleanSetting outline = new BooleanSetting("outline", this, true);

	public ColorSetting obbyColor = new ColorSetting("obbyColor", this, new JColor(0, 121, 194, 50));
	public ColorSetting bedrockColor = new ColorSetting("bedrockColor", this, new JColor(0, 200, 255, 50));
	
	public HoleEsp() {
		super ("holeEsp", "shows an esp inobby and bedrock holes.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(size, outline, bedrockColor, obbyColor);
	}

	private static final Minecraft mc = Wrapper.getMinecraft();

	private final BlockPos[] surroundOffset ={
			new BlockPos(0, -1, 0), // down
			new BlockPos(0, 0, -1), // north
			new BlockPos(1, 0, 0), // east
			new BlockPos(0, 0, 1), // south
			new BlockPos(-1, 0, 0) // west
	};

	private ConcurrentHashMap<BlockPos, Boolean> safeHoles;

	public List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
		List<BlockPos> circleblocks = new ArrayList<>();
		int cx = loc.getX();
		int cy = loc.getY();
		int cz = loc.getZ();
		for (int x = cx - (int) r; x <= cx + r; x++){
			for (int z = cz - (int) r; z <= cz + r; z++){
				for (int y = (sphere ? cy - (int) r : cy); y < (sphere ? cy + r : cy + h); y++){
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
					if (dist < r * r && !(hollow && dist < (r - 1) * (r - 1))){
						BlockPos l = new BlockPos(x, y + plus_y, z);
						circleblocks.add(l);
					}
				}
			}
		}
		return circleblocks;
	}

	public static BlockPos getPlayerPos() {
		return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
	}

	@Override
	public void onUpdate() {
		if (safeHoles == null) {
			safeHoles = new ConcurrentHashMap<>();
		}
		else{
			safeHoles.clear();
		}

		int range = (int) Math.ceil(8);

		List<BlockPos> blockPosList = getSphere(getPlayerPos(), range, range, false, true, 0);
		for (BlockPos pos : blockPosList){

			if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)){
				continue;
			}
			if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
				continue;
			}
			if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
				continue;
			}

			boolean isSafe = true;
			boolean isBedrock = true;

			for (BlockPos offset : surroundOffset) {
				Block block = mc.world.getBlockState(pos.add(offset)).getBlock();
				if (block != Blocks.BEDROCK){
					isBedrock = false;
				}
				if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
					isSafe = false;
					break;
				}
			}
			if (isSafe){
				safeHoles.put(pos, isBedrock);
			}
		}
	}

	@Override
	public void onWorldRender(final RenderEvent event) {
		if (mc.player == null || safeHoles == null){
			return;
		}
		if (safeHoles.isEmpty()) {
			return;
		}
		
		safeHoles.forEach((blockPos, isBedrock) -> {
			drawBox(blockPos,1, isBedrock);
		});
		safeHoles.forEach((blockPos, isBedrock) -> {
			drawOutline(blockPos,2,isBedrock);
		});
	}

	private JColor getColor (boolean isBedrock) {
		JColor c;
		if (isBedrock) c= bedrockColor.getValue();
		else c= obbyColor.getValue();
		return new JColor(c);
	}

	private void drawBox(BlockPos blockPos, int width, boolean isBedrock) {
			JColor color=getColor(isBedrock);
			JTessellator.drawBox(blockPos, size.getValue(), color, GeometryMasks.Quad.ALL);
		}

	private void drawOutline(BlockPos blockPos, int width, boolean isBedrock) {
		JColor color=getColor(isBedrock);
		if(outline.isEnabled()) {
			JTessellator.drawBoundingBox(blockPos, size.getValue(), width, color);
		}
	}

}
