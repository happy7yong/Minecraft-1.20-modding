package net.hipyu.tutorialmod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // '/addcoins <amount>' 명령어 등록
        dispatcher.register(Commands.literal("addcoins")
                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                        .executes(context -> addCoins(context.getSource(), IntegerArgumentType.getInteger(context, "amount")))));
    }

    private static int addCoins(CommandSourceStack source, int amount) throws CommandSyntaxException {
        CoinOverlay.setCoins(CoinOverlay.getCoins() + amount);
        source.sendSuccess(() -> Component.literal("Added " + amount + " coins!"), true);
        return 1;
    }
}
