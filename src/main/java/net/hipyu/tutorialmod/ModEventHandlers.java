package net.hipyu.tutorialmod;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        // 클라이언트 사이드에서만 처리
        if (event.getLevel().isClientSide) {
            BlockPos pos = event.getPos();
            BlockState blockState = event.getLevel().getBlockState(pos);

            // 자작나무 블록인지 확인
            if (blockState.is(Blocks.BIRCH_LOG)) {
                // 메시지를 띄우는 코드
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("You right-clicked on a Birch Log!"));

                // 이미지 띄우는 GUI 호출
                Minecraft.getInstance().setScreen(new CustomImageScreen());

                // 이벤트가 처리되었음을 알리기 위해 InteractionResult를 반환
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}
