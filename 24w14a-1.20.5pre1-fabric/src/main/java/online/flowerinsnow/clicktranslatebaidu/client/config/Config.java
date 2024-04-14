package online.flowerinsnow.clicktranslatebaidu.client.config;

import cc.carm.lib.configuration.core.ConfigurationRoot;
import cc.carm.lib.configuration.core.annotation.HeaderComment;
import cc.carm.lib.configuration.core.value.type.ConfiguredValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Config extends ConfigurationRoot {
    @HeaderComment("Enable or not")
    public static final ConfiguredValue<Boolean> ENABLE = ConfiguredValue.of(Boolean.class, true);
    @HeaderComment("Text of translate button")
    public static final ConfiguredValue<String> TEXT = ConfiguredValue.of(String.class, "&a [T]");
    @HeaderComment("Split char, substring message from the first split char")
    public static final ConfiguredValue<String> SPLIT_CHAR = ConfiguredValue.of(String.class, ":");
    @HeaderComment("API Address")
    public static final ConfiguredValue<String> API = ConfiguredValue.of(String.class, "https://fanyi-api.baidu.com/api/trans/vip/translate");
    @HeaderComment("Obtain from https://fanyi-api.baidu.com/manage/developer . If it is a pure number, you need to add English double quotes")
    public static final ConfiguredValue<String> APP_ID = ConfiguredValue.of(String.class, "unfilled");
    @HeaderComment("Obtain from https://fanyi-api.baidu.com/manage/developer .")
    public static final ConfiguredValue<String> APP_SECRET = ConfiguredValue.of(String.class, "unfilled");
    @HeaderComment("Code of target language，View full codes from https://fanyi-api.baidu.com/doc/21 .")
    public static final ConfiguredValue<String> TO_LANGUAGE = ConfiguredValue.of(String.class, "zh");
}
