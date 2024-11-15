package cn.ksmcbrigade.scc;

import cn.ksmcbrigade.scb.uitls.CommandUtils;
import cn.ksmcbrigade.scc.commands.TpCommand;
import cn.ksmcbrigade.scc.commands.creativeOnly.copyCommand;
import cn.ksmcbrigade.scc.commands.creativeOnly.enchantCommand;
import cn.ksmcbrigade.scc.commands.creativeOnly.giveCommand;
import cn.ksmcbrigade.scc.commands.creativeOnly.potion.*;
import cn.ksmcbrigade.scc.commands.dropAllItemsCommand;
import cn.ksmcbrigade.scc.commands.dropItemCommand;
import cn.ksmcbrigade.scc.commands.sayCommand;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleClientCommands.MODID)
public class SimpleClientCommands
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "scc";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SimpleClientCommands(IEventBus modEventBus, ModContainer modContainer)
    {
        CommandUtils.add(new sayCommand());
        CommandUtils.add(new TpCommand());

        CommandUtils.add(new dropItemCommand());
        CommandUtils.add(new dropAllItemsCommand());

        CommandUtils.add(new enchantCommand());

        CommandUtils.add(new allEffectsPotionCommand());

        CommandUtils.add(new beneficialEffectsPotionCommand());
        CommandUtils.add(new beneficialNeutralEffectsPotionCommand());

        CommandUtils.add(new hardEffectsPotionCommand());
        CommandUtils.add(new hardNeutralEffectsPotionCommand());

        CommandUtils.add(new neutralEffectsPotionCommand());

        CommandUtils.add(new killEffectPotionCommand());
        CommandUtils.add(new killEffectPotion2Command());

        CommandUtils.add(new healEffectPotionCommand());

        CommandUtils.add(new giveCommand());
        CommandUtils.add(new copyCommand());
    }
}
