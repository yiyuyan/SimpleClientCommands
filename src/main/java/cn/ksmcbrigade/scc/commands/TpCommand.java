package cn.ksmcbrigade.scc.commands;

import cn.ksmcbrigade.scb.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class TpCommand extends Command {
    public TpCommand() {
        super("tp",3);
    }

    @Override
    public void onCommand(Minecraft MC, Player player, String[] args) {
        if(player==null) return;
        Vec3 pos;
        try {
            pos = new Vec3(args[0].contains("~")?player.getX()+to(args[0].replace("~","")):Double.parseDouble(args[0]),
                    args[1].contains("~")?player.getY()+to(args[1].replace("~","")):Double.parseDouble(args[1]),
                    args[2].contains("~")?player.getZ()+to(args[2].replace("~","")):Double.parseDouble(args[2]));
        } catch (NumberFormatException e) {
            player.sendSystemMessage(Component.literal("Can't convert command args to vec3."));
            return;
        }
        player.setPos(pos.x,pos.y,pos.z);
        player.moveTo(pos);
        if(MC.getConnection()!=null){
            MC.getConnection().send(new ServerboundMovePlayerPacket.Pos(pos.x,pos.y,pos.z,player.onGround()));
        }
    }

    public double to(String target) throws NumberFormatException{
        if(target.isEmpty()){
            return 0D;
        }
        else{
            return Double.parseDouble(target);
        }
    }
}
