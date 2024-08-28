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
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    private static final long DOUBLE_CLICK_TIME_LIMIT = 300; // 밀리초 단위
    private static long lastSpacebarClickTime = 0;
    //G키 누르면 이미지 발생
    @SubscribeEvent
    public static void onKeyPress(InputEvent.@NotNull Key event) {
        // G 키가 눌렸는지 확인
        if (event.getKey() == GLFW.GLFW_KEY_G && event.getAction() == GLFW.GLFW_PRESS) {
            Minecraft mc = Minecraft.getInstance();

            // 현재 열려 있는 화면을 확인
            if (mc.screen != null) {
                // 채팅 창 또는 다른 GUI가 열려 있을 때 키 입력 무시
                return;
            }

            // 클라이언트의 로컬 플레이어 가져오기
            LocalPlayer player = mc.player;

            if (player != null) {
                // 메시지 띄우기
                Minecraft.getInstance().player.connection.sendCommand("/테스트");

                // 이미지 띄우는 GUI 호출
                Minecraft.getInstance().setScreen(new RuleImageScreen());
            }
        }
        // 스페이스바 두 번 클릭 감지
        if (event.getKey() == GLFW.GLFW_KEY_SPACE && event.getAction() == GLFW.GLFW_PRESS) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastSpacebarClickTime <= DOUBLE_CLICK_TIME_LIMIT) {
                // 두 번째 스페이스바 클릭 감지
                sendChatMessage();
            }

            // 현재 클릭 시간을 기록
            lastSpacebarClickTime = currentTime;
        }
    }

    private static void sendChatMessage() {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player != null) {
            // 채팅 메시지 전송
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("dd"));
        }
    }



    //자작나무 우클릭시 이미지 발생
    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.@NotNull RightClickBlock event) {
        // 클라이언트 사이드에서만 처리
        if (event.getLevel().isClientSide) {
            BlockPos pos = event.getPos();
            BlockState blockState = event.getLevel().getBlockState(pos);

            // 자작나무 블록인지 확인
            if (blockState.is(Blocks.BIRCH_LOG)) {
                // 메시지를 띄우는 코드
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(""));

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
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(""));

                // RuleImageScreen GUI 호출
                Minecraft.getInstance().setScreen(new RuleImageScreen());

                // 이벤트가 처리되었음을 알리기 위해 InteractionResult를 반환
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }


    }
}