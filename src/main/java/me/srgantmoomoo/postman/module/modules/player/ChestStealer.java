package me.srgantmoomoo.postman.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.api.util.misc.TimeHelper;
import me.srgantmoomoo.api.util.misc.Timer;
import me.srgantmoomoo.postman.module.Category;
import me.srgantmoomoo.postman.module.Module;
import me.srgantmoomoo.postman.settings.ModeSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;

public class ChestStealer extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "steal", "steal", "drop");
	
	public TimeHelper time = new TimeHelper();
	private Minecraft mc = Minecraft.getMinecraft();
    private Timer timer = new Timer();
	
	public ChestStealer() {
		super ("chestStealer", "slows down ur hungerness", Keyboard.KEY_NONE, Category.EXPLOITS);
		this.addSettings(mode);
	}
	
	public void onUpdate() {
		
		if((this.mc.player.openContainer != null) && ((this.mc.player.openContainer instanceof ContainerChest))) {
			ContainerChest chest = (ContainerChest) this.mc.player.openContainer;
			for(int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); ++i) {
				if(mode.getMode().equals("steal")) {
				if((chest.getLowerChestInventory().getStackInSlot(i) != null)) {
					this.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.QUICK_MOVE, this.mc.player);
				}
				if(chest.getInventory().isEmpty()) {
					this.mc.displayGuiScreen(null);
				}
				}
				
				if(mode.getMode().equals("drop")) {
				if((chest.getLowerChestInventory().getStackInSlot(i) != null)) {
					this.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.THROW, this.mc.player);
				}
				if(chest.getInventory().isEmpty()) {
					this.mc.displayGuiScreen(null);
				}
				}
			}
		}
		
		if((this.mc.player.openContainer != null) && ((this.mc.player.openContainer instanceof ContainerHorseInventory ))) {
			ContainerHorseInventory chest = (ContainerHorseInventory) this.mc.player.openContainer;
			  for (int i = 0; i < ((IInventory) chest.getInventory()).getSizeInventory(); ++i) {
				  if(mode.equals("steal")) {
					  if((((IInventory) chest.getInventory()).getStackInSlot(i) != null)) {
							this.mc.playerController.windowClick(chest.windowId, i, 0, ClickType.QUICK_MOVE, this.mc.player);
					  }
				  }
			  }
			  
			  if(chest.getInventory().isEmpty()) {
					this.mc.displayGuiScreen(null);
			  }
		}
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
}
		/*
		 * for (int l_I = 0; l_I < l_Chest.lowerChestInventory.getSizeInventory(); ++l_I)	
		 */
	
	/*private Minecraft mc = Minecraft.getMinecraft();

    private Timer timer = new Timer();

    @Override
    public String getMetaData()
    {
        return Mode.getValue().toString();
    }

    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate = new Listener<>(p_Event ->
    {
        if (!timer.passed(Delay.getValue() * 100f))
            return;

        timer.reset();

        if (mc.currentScreen instanceof GuiChest)
        {
            GuiChest l_Chest = (GuiChest) mc.currentScreen;

            for (int l_I = 0; l_I < l_Chest.lowerChestInventory.getSizeInventory(); ++l_I)
            {
                ItemStack l_Stack = l_Chest.lowerChestInventory.getStackInSlot(l_I);

                if ((l_Stack.isEmpty() || l_Stack.getItem() == Items.AIR) && Mode.getValue() == Modes.Store)
                {
                    HandleStoring(l_Chest.inventorySlots.windowId, l_Chest.lowerChestInventory.getSizeInventory() - 9);
                    return;
                }

                if (Shulkers.getValue() && !(l_Stack.getItem() instanceof ItemShulkerBox))
                    continue;
                
                if (l_Stack.isEmpty())
                    continue;

                switch (Mode.getValue())
                {
                    case Steal:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, 0, ClickType.QUICK_MOVE, mc.player);
                        return;
                    case Drop:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, -999, ClickType.THROW, mc.player);
                        return;
                    default:
                        break;
                }
            }
        }
        else if (mc.currentScreen instanceof GuiScreenHorseInventory && EntityChests.getValue())
        {
            GuiScreenHorseInventory l_Chest = (GuiScreenHorseInventory)mc.currentScreen;
            
            for (int l_I = 0; l_I < l_Chest.horseInventory.getSizeInventory(); ++l_I)
            {
                ItemStack l_Stack = l_Chest.horseInventory.getStackInSlot(l_I);

                if ((l_Stack.isEmpty() || l_Stack.getItem() == Items.AIR) && Mode.getValue() == Modes.Store)
                {
                    HandleStoring(l_Chest.inventorySlots.windowId, l_Chest.horseInventory.getSizeInventory() - 9);
                    return;
                }

                if (Shulkers.getValue() && !(l_Stack.getItem() instanceof ItemShulkerBox))
                    continue;
                
                if (l_Stack.isEmpty())
                    continue;

                switch (Mode.getValue())
                {
                    case Steal:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, 0, ClickType.QUICK_MOVE, mc.player);
                        return;
                    case Drop:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, -999, ClickType.THROW, mc.player);
                        return;
                    default:
                        break;
                }
            }
        }
        else if (mc.currentScreen instanceof GuiShulkerBox && Shulkers.getValue())
        {
            GuiShulkerBox l_Chest = (GuiShulkerBox)mc.currentScreen;
            
            for (int l_I = 0; l_I < l_Chest.inventory.getSizeInventory(); ++l_I)
            {
                ItemStack l_Stack = l_Chest.inventory.getStackInSlot(l_I);

                if ((l_Stack.isEmpty() || l_Stack.getItem() == Items.AIR) && Mode.getValue() == Modes.Store)
                {
                    HandleStoring(l_Chest.inventorySlots.windowId, l_Chest.inventory.getSizeInventory() - 9);
                    return;
                }

                if (Shulkers.getValue() && !(l_Stack.getItem() instanceof ItemShulkerBox))
                    continue;

                if (l_Stack.isEmpty())
                    continue;
                
                switch (Mode.getValue())
                {
                    case Steal:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, 0, ClickType.QUICK_MOVE, mc.player);
                        return;
                    case Drop:
                        mc.playerController.windowClick(l_Chest.inventorySlots.windowId, l_I, -999, ClickType.THROW, mc.player);
                        return;
                    default:
                        break;
                }
            }
        }
    });

    private void HandleStoring(int p_WindowId, int p_Slot)
    {
        if (Mode.getValue() == Modes.Store)
        {
            for (int l_Y = 9; l_Y < mc.player.inventoryContainer.inventorySlots.size() - 1; ++l_Y)
            {
                ItemStack l_InvStack = mc.player.inventoryContainer.getSlot(l_Y).getStack();

                if (l_InvStack.isEmpty() || l_InvStack.getItem() == Items.AIR)
                    continue;

                if (Shulkers.getValue() && !(l_InvStack.getItem() instanceof ItemShulkerBox))
                    continue;

                mc.playerController.windowClick(p_WindowId, l_Y + p_Slot, 0, ClickType.QUICK_MOVE, mc.player);
                return;
            }
        }
    }
} */


