package online.flowerinsnow.clicktranslatebaidu.client;

import cc.carm.lib.configuration.EasyConfiguration;
import cc.carm.lib.configuration.core.source.ConfigurationProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import online.flowerinsnow.clicktranslatebaidu.client.config.Config;
import online.flowerinsnow.clicktranslatebaidu.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslatebaidu.client.listener.ChatHudAddMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class ClickTranslateBaiduClient implements ClientModInitializer {
    public static final String PROTOCOL = "click-translate-baidu://";
    public static final Logger LOGGER = LoggerFactory.getLogger("Click Translate Baidu");

    @Override
    public void onInitializeClient() {
        saveDefaultConfig();
        reloadConfig();

        ChatHudAddMessageCallback.EVENT.register(new ChatHudAddMessageListener());
    }

    private static ConfigurationProvider<?> configProvider;

    public static void saveDefaultConfig() {
        configProvider = EasyConfiguration.from(FabricLoader.getInstance().getConfigDir().resolve("click-translate-baidu.yml").toFile());
        configProvider.initialize(Config.class);
    }

    public static void reloadConfig() {
        try {
            configProvider.reload();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
