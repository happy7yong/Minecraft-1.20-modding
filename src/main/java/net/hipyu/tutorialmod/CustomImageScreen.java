package net.hipyu.tutorialmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;

public class CustomImageScreen extends Screen {

    private static final ResourceLocation IMAGE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/example_image.png");

    protected CustomImageScreen() {
        super(Component.literal("Custom Image Screen"));
    }

    @Override
    protected void init() {
        super.init();

        // Close 버튼 추가
        this.addRenderableWidget(Button.builder(Component.literal("Close"), button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20)  // 버튼 위치와 크기 설정
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // 배경 그리기
        this.renderBackground(guiGraphics);

        // 이미지 그리기
        RenderSystem.setShaderTexture(0, IMAGE);  // 텍스처를 설정
        guiGraphics.blit(IMAGE, this.width / 2 - 100, this.height / 2 - 100, 0, 0, 200, 200, 200, 200);  // 이미지 렌더링

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
