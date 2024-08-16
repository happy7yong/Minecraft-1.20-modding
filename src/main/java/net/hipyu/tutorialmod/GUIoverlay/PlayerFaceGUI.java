package net.hipyu.tutorialmod.GUIoverlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.hipyu.tutorialmod.TutorialMod;
import net.minecraft.network.chat.Component;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT)
public class PlayerFaceGUI {

    @SubscribeEvent
    public static void onRenderPlayerFace(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayer player = mc.player;

        // 플레이어가 존재하는 경우에만 렌더링
        if (player != null) {
            renderPlayerFace(event.getGuiGraphics(), player, mc);
        } else {
            sendChatMessage("플레이어가 존재하지 않습니다.");
        }
    }

    private static void renderPlayerFace(GuiGraphics guiGraphics, AbstractClientPlayer player, Minecraft mc) {
        // 플레이어의 스킨 텍스처 가져오기
        ResourceLocation skin = player.getSkinTextureLocation();

        // 스킨 텍스처가 유효한지 확인
        if (skin == null) {
            sendChatMessage("스킨 텍스처를 로드할 수 없습니다. 기본 스킨 사용 중.");
            return; // 스킨이 없으면 렌더링 중단
        }

        // 화면 너비 가져오기
        int screenWidth = mc.getWindow().getGuiScaledWidth();

        // 플레이어 얼굴의 위치 및 크기 설정
        int x = screenWidth - 50; // 오른쪽 끝에서 약간 떨어진 위치 (50 픽셀)
        int y = 10; // Y 위치
        int size = 32; // 얼굴 크기 (32x32)

        // 스킨 텍스처를 설정하고 블렌드 모드 활성화
        RenderSystem.setShaderTexture(0, skin);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc(); // 기본 블렌드 함수

        // 얼굴을 화면에 렌더링
        guiGraphics.blit(skin, x, y, 8, 8, size, size, 64, 64); // 스킨의 얼굴 부분을 8, 8에서 크기 32x32로 잘라서 표시

        // 블렌드 모드 비활성화
        RenderSystem.disableBlend();
    }

    private static void sendChatMessage(String message) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(message));
    }
}
