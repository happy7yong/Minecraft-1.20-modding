package net.hipyu.tutorialmod;

import net.hipyu.tutorialmod.GUIoverlay.CoinOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    public static void init(final FMLClientSetupEvent event) {
        // 클라이언트 전용 이벤트 등록
        MinecraftForge.EVENT_BUS.register(CoinOverlay.class);
    }
}
