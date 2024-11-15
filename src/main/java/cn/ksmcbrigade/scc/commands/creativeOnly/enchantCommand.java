package cn.ksmcbrigade.scc.commands.creativeOnly;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class enchantCommand  extends CreativeOnlyCommand {
    public enchantCommand() {
        super("enchant",0,(MC,player,args)->{
            ItemStack last = player.getInventory().getSelected();
            ItemStack stack = last.copy();
            if(stack.isEmpty()) {
                player.sendSystemMessage(Component.literal("selected item is empty!").withStyle(ChatFormatting.RED));
                return;
            }
            if(args.length<1){
                player.level().registryAccess().registry(Registries.ENCHANTMENT).get().holders().forEach(enchantmentReference -> stack.enchant(enchantmentReference.getDelegate(),127));
                System.out.println(stack.getEnchantments());
                if(MC.getConnection()!=null){
                    int i = 0;
                    for (int i1 = 0; i1 < player.inventoryMenu.slots.size(); i1++) {
                        Slot slot = player.inventoryMenu.slots.get(i1);
                        if(slot.getItem().equals(last)){
                            i = i1;
                        }
                    }
                    MC.getConnection().getConnection().send(new ServerboundSetCreativeModeSlotPacket(i,stack));
                }
            } else if (args.length==1) {
                player.level().registryAccess().registry(Registries.ENCHANTMENT).get().holders().filter(enchantmentReference -> Objects.requireNonNull(enchantmentReference.getDelegate().getKey()).location().toString().equalsIgnoreCase(args[0])).forEach(enchantmentReference -> stack.enchant(enchantmentReference.getDelegate(),1));
                if(MC.getConnection()!=null){
                    int i = 0;
                    for (int i1 = 0; i1 < player.inventoryMenu.slots.size(); i1++) {
                        Slot slot = player.inventoryMenu.slots.get(i1);
                        if(slot.getItem().equals(last)){
                            i = i1;
                        }
                    }
                    MC.getConnection().getConnection().send(new ServerboundSetCreativeModeSlotPacket(i,stack));
                }
            }
            else{
                player.level().registryAccess().registry(Registries.ENCHANTMENT).get().holders().filter(enchantmentReference -> Objects.requireNonNull(enchantmentReference.getDelegate().getKey()).location().toString().equalsIgnoreCase(args[0])).forEach(enchantmentReference -> stack.enchant(enchantmentReference.getDelegate(),Integer.parseInt(args[1])));
                if(MC.getConnection()!=null){
                    int i = 0;
                    for (int i1 = 0; i1 < player.inventoryMenu.slots.size(); i1++) {
                        Slot slot = player.inventoryMenu.slots.get(i1);
                        if(slot.getItem().equals(last)){
                            i = i1;
                        }
                    }
                    MC.getConnection().getConnection().send(new ServerboundSetCreativeModeSlotPacket(i,stack));
                }
            }
        });
    }
}
