package net.hipyu.tutorialmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;

public class CustomImageScreen extends Screen {

    private static final ResourceLocation IMAGE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/example_image.png");
    private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/custom-button.png");

    protected CustomImageScreen() {
        super(Component.literal("Custom Image Screen"));
    }

    @Override
    protected void init() {
        super.init();

        // 기본 버튼 추가
        this.addRenderableWidget(Button.builder(Component.literal("Close"), button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20) // 버튼 위치와 크기
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // 배경 그리기
        this.renderBackground(guiGraphics);

        // 이미지 그리기
        guiGraphics.blit(IMAGE, this.width / 2 - 100, this.height / 2 - 100, 0, 0, 200, 200, 200, 200);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
