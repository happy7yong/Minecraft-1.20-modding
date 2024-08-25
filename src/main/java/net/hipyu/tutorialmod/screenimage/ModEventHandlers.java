package net.hipyu.tutorialmod.screenimage;

import net.hipyu.tutorialmod.TutorialMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    //G키 누르면 이미지 발생
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        // G 키가 눌렸는지 확인
        if (event.getKey() == GLFW.GLFW_KEY_G && event.getAction() == GLFW.GLFW_PRESS) {
            // 클라이언트의 로컬 플레이어 가져오기
            LocalPlayer player = Minecraft.getInstance().player;

            if (player != null) {
                // 메시지 띄우기
                player.sendSystemMessage(Component.literal("g키를 누르셨습니다"));

                // 이미지 띄우는 GUI 호출
                Minecraft.getInstance().setScreen(new RuleImageScreen());
            }
        }
    }

    //자작나무 우클릭시 이미지 발생
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

    //아이템 우클릭 이벤트
    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        // 클라이언트 사이드에서만 처리
        if (event.getLevel().isClientSide()) {
            // 플레이어가 우클릭한 아이템이 책인지 확인
            if (event.getItemStack().getItem() == Items.NETHER_STAR) {
                // 메시지를 띄우는 코드 (선택 사항)
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("You right-clicked with a Book!"));

                // RuleImageScreen GUI 호출
                Minecraft.getInstance().setScreen(new RuleImageScreen());

                // 이벤트가 처리되었음을 알리기 위해 InteractionResult를 반환
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}