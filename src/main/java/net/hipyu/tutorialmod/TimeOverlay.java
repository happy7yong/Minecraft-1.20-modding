package net.hipyu.tutorialmod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tutorialmod", value = Dist.CLIENT)
public class TimeOverlay {

    private static int timerSeconds = 0;
    private static long startTime = 0;
    private static boolean timerCompleted = false;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (timerSeconds > 0) {
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            PoseStack poseStack = event.getGuiGraphics().pose(); // GuiGraphics의 pose() 메서드를 사용

            // 경과 시간 계산
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            int remainingTime = timerSeconds - (int) elapsedTime;
            if (remainingTime <= 0) {
                remainingTime = 0;
                timerSeconds = 0; // 타이머 완료 후 멈춤
                if (!timerCompleted) {
                    // 타이머가 완료되면 채팅 메시지 전송
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("타이머가 완료되었습니다!"));
                    timerCompleted = true;
                }
            } else {
                // 타이머 완료 상태가 아닌 경우
                timerCompleted = false;
            }

            // 분과 초로 변환
            int minutes = remainingTime / 60;
            int seconds = remainingTime % 60;

            // 표시할 텍스트
            String timerText = String.format("%02d:%02d", minutes, seconds);

            // 오른쪽 상단에 텍스트를 그리기
            int x = 20; // 오른쪽 끝에서 약간 떨어진 위치
            int y = 50;
            int color = 0xFFFFFF; // 흰색

            RenderSystem.enableBlend();
            // GuiGraphics의 drawString 메서드를 사용하여 텍스트 그리기
            event.getGuiGraphics().drawString( fontRenderer, timerText, x, y, color);
            RenderSystem.disableBlend();
        }
    }

    public static void setTimer(int seconds) {
        timerSeconds = seconds;
        startTime = System.currentTimeMillis();
        timerCompleted = false; // 타이머가 설정되면 완료 상태를 초기화
    }
}
