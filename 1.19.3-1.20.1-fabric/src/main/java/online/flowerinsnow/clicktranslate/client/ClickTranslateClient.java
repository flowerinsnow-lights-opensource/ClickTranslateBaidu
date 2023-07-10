package online.flowerinsnow.clicktranslate.client;

import cc.carm.lib.configuration.EasyConfiguration;
import cc.carm.lib.configuration.hocon.HOCONFileConfigProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import online.flowerinsnow.clicktranslate.client.config.Config;
import online.flowerinsnow.clicktranslate.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslate.client.listener.ChatHudAddMessageListener;

@Environment(EnvType.CLIENT)
public class ClickTranslateClient implements ClientModInitializer {
    public static final String PROTOCOL = "clicktranslate://";

    @Override
    public void onInitializeClient() {
        saveDefaultConfig();
        reloadConfig();

        ChatHudAddMessageCallback.EVENT.register(new ChatHudAddMessageListener());
    }

    private static HOCONFileConfigProvider configProvider;

    public static void saveDefaultConfig() {
        configProvider = EasyConfiguration.from(FabricLoader.getInstance().getConfigDir().resolve("clicktranslate.conf").toFile());
        configProvider.initialize(Config.class);
    }

    public static void reloadConfig() {
        try {
            configProvider.reload();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
