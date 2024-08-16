package net.hipyu.tutorialmod;

import com.mojang.logging.LogUtils;
import net.hipyu.tutorialmod.GUIoverlay.HealthGUI;
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

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the event handler
        MinecraftForge.EVENT_BUS.register(ModEventHandlers.class);

        // 클라이언트 전용 이벤트 핸들러 등록
        modEventBus.addListener(ClientModEvents::init);

        // Register command events
        MinecraftForge.EVENT_BUS.register(ModCommands.class);

        // 이벤트 버스에 리스너 등록
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
    }
    private void setup(final FMLCommonSetupEvent event) {
        // 서버 측 초기화 코드 (필요시)
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // 클라이언트 측 초기화 코드
        // 여기에서 이벤트 리스너 등록
        MinecraftForge.EVENT_BUS.register(new HealthGUI());
    }
}
