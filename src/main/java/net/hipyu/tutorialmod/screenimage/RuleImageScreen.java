package net.hipyu.tutorialmod.screenimage;

import net.hipyu.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RuleImageScreen extends Screen {

    private static final int TOTAL_PAGES = 14;  // 총 페이지 수
    private int currentPage = 1;  // 현재 페이지 번호

    protected RuleImageScreen() {
        super(Component.literal("Rule Image Screen"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);

        // 현재 페이지에 맞는 이미지 로드
        ResourceLocation ruleImage = new ResourceLocation(TutorialMod.MOD_ID, "textures/rule/" + String.format("%03d", currentPage) + ".png");

        int imageWidth = this.width;
        int imageHeight = this.height;
        int imageX = (this.width - imageWidth) / 2;
        int imageY = (this.height - imageHeight) / 2;

        guiGraphics.blit(ruleImage, imageX, imageY, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {  // 좌클릭
            previousPage();
            return true;
        } else if (button == 1) {  // 우클릭
            nextPage();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void nextPage() {
        if (currentPage < TOTAL_PAGES) {
            currentPage++;
        } else {
            this.onClose();  // 마지막 페이지에서 닫기
        }
    }

    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }
}
