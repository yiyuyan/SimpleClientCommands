package cn.ksmcbrigade.scc.commands.creativeOnly.potion;

import cn.ksmcbrigade.scc.command.CreativeOnlyCommand;
import cn.ksmcbrigade.scc.utils.ItemUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.lang.reflect.Field;
import java.util.Objects;

public class neutralEffectsPotionCommand extends CreativeOnlyCommand {
    public neutralEffectsPotionCommand() {
        super("neu-ep",(MC,player,args)->{
            ItemStack stack = Items.SPLASH_POTION.getDefaultInstance();
            for (Field declaredField : MobEffects.class.getDeclaredFields()) {
                if(declaredField.getType().equals(Holder.class)){
                    declaredField.setAccessible(true);
                    Holder<MobEffect> effect = (Holder<MobEffect>) declaredField.get(null);
                    if(effect.value().getCategory().equals(MobEffectCategory.NEUTRAL)){
                        stack.set(DataComponents.POTION_CONTENTS,stack.get(DataComponents.POTION_CONTENTS).withEffectAdded(new MobEffectInstance(effect,-1,127)));
                    }
                }
            }
            int i = ItemUtils.getFree(player.getInventory());

            if(i==-999){
                player.sendSystemMessage(Component.literal("Can't find the free slot.").withStyle(ChatFormatting.RED));
                return;
            }
            ItemUtils.set(i,stack, Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection());

        });
    }
}
