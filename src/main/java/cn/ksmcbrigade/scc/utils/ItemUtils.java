package cn.ksmcbrigade.scc.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemUtils {

    public static int get(ItemStack itemStack, InventoryMenu inventoryMenu){
        for (int i = 0; i < inventoryMenu.slots.size(); i++) {
            Slot slot = inventoryMenu.slots.get(i);
            if(slot.getItem().equals(itemStack)){
                return i;
            }
        }
        return -999;
    }

    public static int getFree(InventoryMenu menu){
        for (int i = 0; i < menu.slots.size(); i++) {
            Slot slot = menu.slots.get(i);
            if(!slot.hasItem()){
                return i;
            }
        }
        return -999;
    }

    public static int toMenu(int inv){
        return switch (inv){
            case 0 -> 36;
            case 1 -> 37;
            case 2 -> 38;
            case 3 -> 39;
            case 4 -> 40;
            case 5 -> 41;
            case 6 -> 42;
            case 7 -> 43;
            case 8 -> 44;
            default -> -999;
        };
    }

    public static int getFree(Inventory inventory){
        return toMenu(inventory.getFreeSlot());
    }

    public static void set(ItemStack slot, ItemStack stack, Connection connection){
        connection.send(new ServerboundSetCreativeModeSlotPacket(get(slot, Minecraft.getInstance().player.inventoryMenu),stack));
    }

    public static void set(InventoryMenu inventoryMenu,ItemStack slot, ItemStack stack, Connection connection){
        connection.send(new ServerboundSetCreativeModeSlotPacket(get(slot,inventoryMenu),stack));
    }

    public static void set(int slot, ItemStack stack, Connection connection){
        connection.send(new ServerboundSetCreativeModeSlotPacket(slot,stack));
    }
}
