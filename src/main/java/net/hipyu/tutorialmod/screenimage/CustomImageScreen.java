package net.hipyu.tutorialmod.screenimage;

import net.hipyu.tutorialmod.TutorialMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;

public class CustomImageScreen extends Screen {

    // 첫 번째 이미지 경로 (배경 이미지)
    private static final ResourceLocation IMAGE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/example_image.png");
    // 기본 버튼 텍스처
    private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/button.png");
    // 마우스가 hover 상태일 때 사용할 버튼 텍스처
    private static final ResourceLocation BUTTON_HOVER = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/button-hover.png");

    // 이미지의 좌표와 크기
    private int buttonX, buttonY, buttonWidth = 100, buttonHeight = 100;

    protected CustomImageScreen() {
        super(Component.literal("Custom Image Screen"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // 배경 그리기
        this.renderBackground(guiGraphics);

        // 첫 번째 이미지 그리기 (배경 이미지)
        int imageX = this.width / 2 - 100;
        int imageY = this.height / 2 - 100;
        guiGraphics.blit(IMAGE, imageX, imageY, 0, 0, 200, 200, 200, 200);

        // 두 번째 이미지 그리기 (버튼 텍스처 또는 hover 텍스처)
        buttonX = this.width / 2 + 120;
        buttonY = this.height / 2 - 100;

        // 마우스가 버튼 이미지 위에 있을 경우 hover 텍스처로 변경
        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
            // hover 상태일 때
            guiGraphics.blit(BUTTON_HOVER, buttonX, buttonY, 0, 0, buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        } else {
            // 기본 상태일 때
            guiGraphics.blit(BUTTON_TEXTURE, buttonX, buttonY, 0, 0, buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    // 마우스 클릭 이벤트 처리
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // 버튼 이미지의 좌표 범위 내에서 클릭되었는지 확인
        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
            this.onClose(); // 버튼 이미지가 클릭되면 onClose() 메서드 실행
            return true; // 클릭이 처리되었음을 반환
        }
        return super.mouseClicked(mouseX, mouseY, button); // 기본 클릭 이벤트 처리
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
