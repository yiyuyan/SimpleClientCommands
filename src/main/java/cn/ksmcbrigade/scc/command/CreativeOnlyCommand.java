package cn.ksmcbrigade.scc.command;

import cn.ksmcbrigade.scb.command.Command;
import cn.ksmcbrigade.scc.utils.CommandConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CreativeOnlyCommand extends Command {

    public final CommandConsumer<Minecraft,Player,String[]> consumer;

    public CreativeOnlyCommand(String name,CommandConsumer<Minecraft,Player,String[]> commandConsumer) {
        super(name);
        this.consumer = commandConsumer;
    }

    public CreativeOnlyCommand(String name, int argsLength, CommandConsumer<Minecraft,Player,String[]> commandConsumer) {
        super(name, argsLength);
        this.consumer = commandConsumer;
    }

    @Override
    public void onCommand(Minecraft MC, Player player, String[] args) throws Exception {
        if(player==null) return;
        if(!player.isCreative()){
            player.sendSystemMessage(Component.literal("This command is creative mode only!").withStyle(ChatFormatting.RED));
            return;
        }
        this.consumer.accept(MC, player, args);
    }
}
