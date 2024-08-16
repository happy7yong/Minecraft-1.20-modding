package net.hipyu.tutorialmod.GUIoverlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class Customhotbar {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player != null) {
            renderCustomHotbar(event.getGuiGraphics(), player, mc);
//            event.setCanceled();  // 기본 핫바 렌더링 취소
        }
    }

    private static void renderCustomHotbar(GuiGraphics guiGraphics, Player player, Minecraft mc) {
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int hotbarX = 10; // 핫바의 X 위치 (왼쪽에 고정)
        int hotbarY = screenHeight / 2 - 91; // 핫바의 Y 시작 위치 (화면 중앙에 고정)

        // 핫바 배경 그리기 (세로로 배치하므로 별도의 배경 그리기 생략 가능)
        RenderSystem.setShaderTexture(0, InventoryScreen.BACKGROUND_LOCATION);

        // 각 슬롯의 아이템을 세로로 렌더링
        for (int i = 0; i < 9; ++i) {
            int slotX = hotbarX; // X 위치는 고정
            int slotY = hotbarY + i * 20; // Y 위치는 슬롯 간 간격을 두고 세로로 정렬
            renderHotbarSlot(guiGraphics, slotX, slotY, player.getInventory().items.get(i), mc);

            // 슬롯 배경 색상 설정 (ARGB 포맷: 0xAARRGGBB)
            guiGraphics.fill(slotX, slotY, slotX + 18, slotY + 18, 0x800000FF); // 반투명 파란색 배경

            // 슬롯에 아이템 렌더링
            renderHotbarSlot(guiGraphics, slotX, slotY, player.getInventory().items.get(i), mc);
        }
    }

    private static void renderHotbarSlot(GuiGraphics guiGraphics, int x, int y, ItemStack stack, Minecraft mc) {
        if (!stack.isEmpty()) {
            guiGraphics.renderItem(stack, x, y); // 아이템 렌더링
            guiGraphics.renderItemDecorations(mc.font, stack, x, y); // 아이템의 수량 텍스트 렌더링
        }
    }
}
