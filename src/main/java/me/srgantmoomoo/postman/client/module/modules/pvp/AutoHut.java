package me.srgantmoomoo.postman.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "automatically builds hut for u.", Keyboard.KEY_NONE, Category.PVP);

		//ending part i lost the code for the rest of it idk why
					}
					
		            mc.playerController.processRightClickBlock(mc.player, mc.world, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
					mc.player.swingArm(EnumHand.MAIN_HAND);
					
		            return true;
				}
				
				return false;
				
		    }

			private int find_in_hotbar() {

		        for (int i = 0; i < 9; ++i) {

		            final ItemStack stack = mc.player.inventory.getStackInSlot(i);

		            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {

		                final Block block = ((ItemBlock) stack.getItem()).getBlock();

		                if (block instanceof BlockEnderChest)
		                    return i;
		                
		                else if (block instanceof BlockObsidian)
		                    return i;
		                
		            }
		        }
		        return -1;
		    }


		}
		
	}

}
