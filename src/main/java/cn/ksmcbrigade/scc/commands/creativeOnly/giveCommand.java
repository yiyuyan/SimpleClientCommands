package cn.ksmcbrigade.scc.commands.creativeOnly;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import cn.ksmcbrigade.scc.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class giveCommand extends CreativeOnlyCommand {
    public giveCommand() {
        super("give",2,(MC,player,args)->{

            int free = ItemUtils.getFree(player.getInventory());

            if(free==-999){
                player.sendSystemMessage(Component.literal("Can't find the free slot.").withStyle(ChatFormatting.RED));
                return;
            }

            ItemStack stack = player.level().registryAccess().registry(Registries.ITEM).get().get(ResourceLocation.parse(args[0])).getDefaultInstance();

            if(stack==null || stack.isEmpty()){
                player.sendSystemMessage(Component.literal("Can't find the item.").withStyle(ChatFormatting.RED));
                return;
            }

            int count;
            try {
                count = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendSystemMessage(Component.literal("Can't con the arg to int.").withStyle(ChatFormatting.RED));
                return;
            }

            stack.setCount(count);

            Objects.requireNonNull(MC.getConnection()).getConnection().send(new ServerboundSetCreativeModeSlotPacket(free,stack));
        });
    }
}
