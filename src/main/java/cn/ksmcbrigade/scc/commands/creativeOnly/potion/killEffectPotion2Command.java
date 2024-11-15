package cn.ksmcbrigade.scc.commands.creativeOnly.potion;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import cn.ksmcbrigade.scc.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class killEffectPotion2Command extends CreativeOnlyCommand {
    public killEffectPotion2Command() {
        super("kill-ep-2",(MC,player,args)->{
            ItemStack stack = Items.SPLASH_POTION.getDefaultInstance();

            stack.set(DataComponents.POTION_CONTENTS,stack.get(DataComponents.POTION_CONTENTS).withEffectAdded(new MobEffectInstance(MobEffects.HARM,Integer.MAX_VALUE,124)));

            stack.set(DataComponents.ITEM_NAME, Component.literal("Kill potion ").withStyle(ChatFormatting.RED).append(CommonComponents.SPACE).append(Component.literal("by ").withStyle(ChatFormatting.BLACK).append(Component.literal("SimpleClient").withStyle(ChatFormatting.BLUE))));

            int i = ItemUtils.getFree(player.getInventory());

            if(i==-999){
                player.sendSystemMessage(Component.literal("Can't find the free slot.").withStyle(ChatFormatting.RED));
                return;
            }
            ItemUtils.set(i,stack, Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection());

        });
    }
}
