package net.hipyu.tutorialmod.GUIoverlay;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HealthGUI {

    // 바닐라 UI 숨기기 위한 이벤트 핸들러 추가
    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().equals(VanillaGuiOverlay.PLAYER_HEALTH.type()) ||
                event.getOverlay().equals(VanillaGuiOverlay.FOOD_LEVEL.type())) {
            event.setCanceled(true); // 기본 체력 및 배고픔 바 렌더링 취소
        }
    }


    @SubscribeEvent
    public void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player != null) {
            // 커스텀 체력 및 배고픔 바 렌더링
            drawHealthBar(event, player, mc);
            drawHungerBar(event, player, mc);
        }
    }

    private void drawHealthBar(RenderGuiOverlayEvent event, Player player, Minecraft mc) {
        int health = (int) player.getHealth();
        int maxHealth = (int) player.getMaxHealth();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int width = 90;
        int height = 20;
        int x = (int) (screenWidth * 0.2); // 화면 왼쪽에서 5% 떨어진 위치
        int y = screenHeight - height - (int) (screenHeight * 0.2); // 화면 아래쪽에서 5% 떨어진 위치



        GuiGraphics guiGraphics = event.getGuiGraphics();
        guiGraphics.fill(x, y, x + width, y + height, 0x80000000); // 배경
        guiGraphics.fill(x, y, x + (width * health / maxHealth), y + height, 0xFFFF0000); // 체력 바

        String healthText = "체력 | " + health + "/" + maxHealth;
        guiGraphics.drawString(mc.font, healthText, x + 5, y + 5, 0xFFFFFFFF); // 체력 텍스트
    }

    private void drawHungerBar(RenderGuiOverlayEvent event, Player player, Minecraft mc) {
        int hunger = player.getFoodData().getFoodLevel();
        int maxHunger = 20; // 최대 배고픔 포인트

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int width = 90;
        int height = 20;
        int x = screenWidth - width - (int) (screenWidth * 0.2); // 화면 오른쪽에서 5% 떨어진 위치
        int y = screenHeight - height - (int) (screenHeight * 0.2); // 화면 아래쪽에서 5% 떨어진 위치



        GuiGraphics guiGraphics = event.getGuiGraphics();
        guiGraphics.fill(x, y, x + width, y + height, 0x80000000); // 배경
        guiGraphics.fill(x, y, x + (width * hunger / maxHunger), y + height, 0xFFDEB887); // 배고픔 바

        String hungerText = "배고픔 | " + hunger + "/" + maxHunger;
        guiGraphics.drawString(mc.font, hungerText, x + 5, y + 5, 0xFFFFFFFF); // 배고픔 텍스트
    }
}
