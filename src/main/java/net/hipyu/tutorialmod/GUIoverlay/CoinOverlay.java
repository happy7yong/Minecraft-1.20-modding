package net.hipyu.tutorialmod.GUIoverlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tutorialmod", value = Dist.CLIENT)
public class CoinOverlay {

    private static int coins = 0; // 현재 코인 수를 저장하는 변수

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;

        // HUD에 표시할 텍스트
        String coinText = "Coins: " + coins;

        // 좌측 상단에 텍스트를 그리기
        int x = 120;
        int y = 10;
        int color = 0xFFFFFF; // 흰색

        RenderSystem.enableBlend();
        event.getGuiGraphics().drawString(fontRenderer, coinText, x, y, color);
        RenderSystem.disableBlend();
    }

    // 코인 수를 설정하는 메소드
    public static void setCoins(int amount) {
        coins = amount;
    }

    // 코인 수를 반환하는 메소드 (필요시)
    public static int getCoins() {
        return coins;
    }
}
