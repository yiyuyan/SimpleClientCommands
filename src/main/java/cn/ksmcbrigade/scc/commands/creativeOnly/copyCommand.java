package cn.ksmcbrigade.scc.commands.creativeOnly;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import cn.ksmcbrigade.scc.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class copyCommand extends CreativeOnlyCommand {
    public copyCommand() {
        super("copy",1,(MC,player,args)->{

            int selected = ItemUtils.toMenu(player.getInventory().selected);
            ItemStack stack = player.getInventory().getSelected().copy();

            if(selected==-999){
                player.sendSystemMessage(Component.literal("Can't con selected inventory slot to inventory menu slot.").withStyle(ChatFormatting.RED));
                return;
            }

            int count;
            try {
                count = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendSystemMessage(Component.literal("Can't con the arg to int.").withStyle(ChatFormatting.RED));
                return;
            }

            stack.setCount(count);

            Objects.requireNonNull(MC.getConnection()).getConnection().send(new ServerboundSetCreativeModeSlotPacket(selected,stack));
        });
    }
}
