package cn.ksmcbrigade.scc.commands.creativeOnly.potion;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import cn.ksmcbrigade.scc.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.lang.reflect.Field;
import java.util.Objects;

public class killEffectPotionCommand extends CreativeOnlyCommand {
    public killEffectPotionCommand() {
        super("kill-ep",(MC,player,args)->{
            ItemStack stack = Items.SPLASH_POTION.getDefaultInstance();

            stack.set(DataComponents.POTION_CONTENTS,stack.get(DataComponents.POTION_CONTENTS).withEffectAdded(new MobEffectInstance(MobEffects.HEAL,Integer.MAX_VALUE,29)));

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
