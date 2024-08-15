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

@Mod.EventBusSubscriber(modid = "tutorialmod")
public class ModCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // '/addcoins <amount>' 명령어 등록
        dispatcher.register(Commands.literal("addcoins")
                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                        .executes(context -> addCoins(context.getSource(), IntegerArgumentType.getInteger(context, "amount")))));

        // '/timer <minutes>' 명령어 등록
        dispatcher.register(Commands.literal("timer")
                .then(Commands.argument("seconds", IntegerArgumentType.integer(1))
                        .executes(context -> setTimer(context.getSource(), IntegerArgumentType.getInteger(context, "seconds")))));
    }

    private static int addCoins(CommandSourceStack source, int amount) throws CommandSyntaxException {
        CoinOverlay.setCoins(CoinOverlay.getCoins() + amount);
        source.sendSuccess(() -> Component.literal("Added " + amount + " coins!"), true);
        return 1;
    }

    private static int setTimer(CommandSourceStack source, int seconds) throws CommandSyntaxException {
        TimeOverlay.setTimer(seconds);
        source.sendSuccess(() -> Component.literal("Timer set for " + seconds + " seconds!"), true);
        return 1;
    }


}
