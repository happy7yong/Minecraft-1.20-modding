package net.hipyu.tutorialmod;

import com.mojang.logging.LogUtils;
import net.hipyu.tutorialmod.GUIoverlay.HealthGUI;
import net.hipyu.tutorialmod.GUIoverlay.PlayerFaceGUI;
import net.hipyu.tutorialmod.commands.ModCommands;
import net.hipyu.tutorialmod.screenimage.ModEventHandlers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 서버 및 클라이언트 공통 이벤트 핸들러 등록
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ModEventHandlers.class);

        // 클라이언트 전용 이벤트 핸들러 등록
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(this::setup);

        // 서버 및 클라이언트 공통 이벤트 핸들러 등록
        MinecraftForge.EVENT_BUS.register(ModCommands.class);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // 서버 측 초기화 코드 (필요시)
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // 클라이언트 측 초기화 코드
        MinecraftForge.EVENT_BUS.register(new HealthGUI());
        MinecraftForge.EVENT_BUS.register(PlayerFaceGUI.class);
    }
}
