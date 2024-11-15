package cn.ksmcbrigade.scc.commands;

import cn.ksmcbrigade.scb.command.Command;
import cn.ksmcbrigade.scc.mixin.PacketListenerMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.LastSeenMessagesTracker;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.SignedMessageBody;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.util.Crypt;
import net.minecraft.world.entity.player.Player;

import java.time.Instant;

public class sayCommand extends Command {
    public sayCommand() {
        super("say",1);
    }

    @Override
    public void onCommand(Minecraft MC, Player player, String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(builder.isEmpty()?"":" ").append(arg);
        }
        if(builder.toString().isEmpty()) return;
        if(MC.getConnection()!=null){
            ClientPacketListener connection = MC.getConnection();
            Instant instant = Instant.now();
            long i = Crypt.SaltSupplier.getLong();
            LastSeenMessagesTracker.Update lastseenmessagestracker$update = ((PacketListenerMixin)connection).getLastMessages().generateAndApplyUpdate();
            MessageSignature messagesignature = ((PacketListenerMixin)connection).getMessageEncoder().pack(new SignedMessageBody(builder.toString(), instant, i, lastseenmessagestracker$update.lastSeen()));
            MC.getConnection().send(new ServerboundChatPacket(builder.toString(),instant,i,messagesignature,lastseenmessagestracker$update.update()));
        }
    }
}
