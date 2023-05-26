package online.flowerinsnow.clicktranslate.client;

import cc.carm.lib.configuration.EasyConfiguration;
import cc.carm.lib.configuration.core.source.ConfigurationProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import online.flowerinsnow.clicktranslate.client.command.CommandClickTranslate;
import online.flowerinsnow.clicktranslate.client.config.Config;
import online.flowerinsnow.clicktranslate.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslate.client.eci.ClientSendCommandCallback;
import online.flowerinsnow.clicktranslate.client.listener.ChatHudAddMessageListener;

public class ClickTranslateClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        saveDefaultConfig();
        reloadConfig();

        ClientSendCommandCallback.EVENT.register(new CommandClickTranslate());
        ChatHudAddMessageCallback.EVENT.register(new ChatHudAddMessageListener());
    }

    private static ConfigurationProvider<?> configProvider;

    public static void saveDefaultConfig() {
        configProvider = EasyConfiguration.from(FabricLoader.getInstance().getConfigDir().resolve("clicktranslate.yml").toFile());
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
