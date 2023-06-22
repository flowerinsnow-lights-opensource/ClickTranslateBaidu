package online.flowerinsnow.clicktranslate.client;

import cc.carm.lib.configuration.EasyConfiguration;
import cc.carm.lib.configuration.hocon.HOCONFileConfigProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import online.flowerinsnow.clicktranslate.client.config.Config;
import online.flowerinsnow.clicktranslate.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslate.client.listener.ChatHudAddMessageListener;

public class ClickTranslateClient implements ClientModInitializer {
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
