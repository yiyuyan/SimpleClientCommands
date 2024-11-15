package cn.ksmcbrigade.scc.commands;

import cn.ksmcbrigade.scb.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.world.entity.player.Player;

public class dropItemCommand extends Command {
    public dropItemCommand() {
        super("drop");
    }

    @Override
    public void onCommand(Minecraft MC, Player player, String[] args) {
        if(MC.getConnection()!=null){
            MC.getConnection().send(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.DROP_ITEM, BlockPos.ZERO, Direction.DOWN));
        }
    }
}
