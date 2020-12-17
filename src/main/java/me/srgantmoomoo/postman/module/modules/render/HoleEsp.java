package me.srgantmoomoo.postman.module.modules.render;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.event.events.RenderEvent;
import me.srgantmoomoo.api.util.Wrapper;
import me.srgantmoomoo.api.util.render.JColor;
import me.srgantmoomoo.api.util.render.JTessellator;
import me.srgantmoomoo.api.util.world.GeometryMasks;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.NumberSetting;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

/*
 * Originally written by someone at gamesense.
 * Heavily modified by @SrgantMooMoo on 11/17/20.
 */

public class HoleEsp extends Module {
	public NumberSetting size = new NumberSetting("size", this, 0.1, 0.0, 1.0, 0.1);
	public NumberSetting bedrockR = new NumberSetting("bedrockR", this, 0, 0, 250, 10);
	public NumberSetting bedrockG = new NumberSetting("bedrockG", this, 200, 0, 250, 10);
	public NumberSetting bedrockB = new NumberSetting("bedrockB", this, 250, 0, 250, 10);
	public NumberSetting obbyR = new NumberSetting("obbyR", this, 0, 0, 250, 10);
	public NumberSetting obbyG = new NumberSetting("obbyG", this, 121, 0, 250, 10);
	public NumberSetting obbyB = new NumberSetting("obbyB", this, 194, 0, 250, 10);
	
	public HoleEsp() {
		super ("holeEsp", "shows an esp in holes in the ground", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(size, bedrockR, bedrockG, bedrockB, obbyR, obbyG, obbyB);
	}

	private static final Minecraft mc = Wrapper.getMinecraft();

	
	//defines the render borders
	private final BlockPos[] surroundOffset ={
			new BlockPos(0, -1, 0), // down
			new BlockPos(0, 0, -1), // north
			new BlockPos(1, 0, 0), // east
			new BlockPos(0, 0, 1), // south
			new BlockPos(-1, 0, 0) // west
	};

	//used to register safe holes for rendering
	private ConcurrentHashMap<BlockPos, Boolean> safeHoles;

	//defines the area for the client to search
	public List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y){
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

	//gets the players location
	public static BlockPos getPlayerPos(){
		return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
	}

	//finds safe holes to render
	@Override
	public void onUpdate(){
		if (safeHoles == null){
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
			if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)){
				continue;
			}
			if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)){
				continue;
			}

			boolean isSafe = true;
			boolean isBedrock = true;

			for (BlockPos offset : surroundOffset){
				Block block = mc.world.getBlockState(pos.add(offset)).getBlock();
				if (block != Blocks.BEDROCK){
					isBedrock = false;
				}
				if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL){
					isSafe = false;
					break;
				}
			}
			if (isSafe){
				safeHoles.put(pos, isBedrock);
			}
		}
	}

	//renders safe holes
	@Override
	public void onWorldRender(final RenderEvent event){
		if (mc.player == null || safeHoles == null){
			return;
		}
		if (safeHoles.isEmpty()){
			return;
		}
		
		safeHoles.forEach((blockPos, isBedrock) -> {
			drawBox(blockPos,1, isBedrock);
		});
		safeHoles.forEach((blockPos, isBedrock) -> {
			drawOutline(blockPos,2,isBedrock);
		});
	}

	private JColor getColor (boolean isBedrock, int alpha) {
		JColor c;
		if (isBedrock) c= new JColor((int) bedrockR.getValue(), (int) bedrockG.getValue(), (int) bedrockB.getValue());
		else c= new JColor((int) obbyR.getValue(), (int) obbyG.getValue(), (int) obbyB.getValue());
		return new JColor(c,alpha);
	}

	//renders fill
	private void drawBox(BlockPos blockPos, int width, boolean isBedrock) {
		JColor color=getColor(isBedrock,50);
		JTessellator.drawBox(blockPos, size.getValue(), color, GeometryMasks.Quad.ALL);
		}

	//renders outline
	private void drawOutline(BlockPos blockPos, int width, boolean isBedrock) {
		JColor color=getColor(isBedrock,50);
					JTessellator.drawBoundingBox(blockPos, size.getValue(), width, color);
	}

}
