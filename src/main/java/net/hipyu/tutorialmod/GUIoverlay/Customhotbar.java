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
        int hotbarY = screenHeight - 30; // 핫바의 Y 위치 (하단에 고정)

        // 핫바 배경 그리기
        RenderSystem.setShaderTexture(0, InventoryScreen.BACKGROUND_LOCATION);
        guiGraphics.blit(InventoryScreen.BACKGROUND_LOCATION, hotbarX, hotbarY, 0, 0, 182, 22, 256, 256);

        // 각 슬롯의 아이템을 렌더링
        for (int i = 0; i < 9; ++i) {
            int slotX = hotbarX + i * 20; // 슬롯 간 간격 조정
            int slotY = hotbarY;
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
