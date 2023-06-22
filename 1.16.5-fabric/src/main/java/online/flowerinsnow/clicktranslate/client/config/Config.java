package online.flowerinsnow.clicktranslate.client.config;

import cc.carm.lib.configuration.core.ConfigurationRoot;
import cc.carm.lib.configuration.core.annotation.HeaderComment;
import cc.carm.lib.configuration.core.value.type.ConfiguredValue;

public class Config extends ConfigurationRoot {
    @HeaderComment("是否启用功能")
    public static final ConfiguredValue<Boolean> ENABLE = ConfiguredValue.of(Boolean.class, true);
    @HeaderComment("显示的按钮")
    public static final ConfiguredValue<String> TEXT = ConfiguredValue.of(String.class, "&a [翻译]");
    @HeaderComment("分割符，截取第一个分隔符后面的文本进行翻译")
    public static final ConfiguredValue<String> SPLIT_CHAR = ConfiguredValue.of(String.class, ":");
    @HeaderComment("API地址，不懂不要修改")
    public static final ConfiguredValue<String> API = ConfiguredValue.of(String.class, "https://fanyi-api.baidu.com/api/trans/vip/translate");
    @HeaderComment("前往 https://fanyi-api.baidu.com/manage/developer 获取")
    public static final ConfiguredValue<String> APP_ID = ConfiguredValue.of(String.class, "未填写");
    @HeaderComment("前往 https://fanyi-api.baidu.com/manage/developer 获取")
    public static final ConfiguredValue<String> APP_SECRET = ConfiguredValue.of(String.class, "未填写");
    @HeaderComment("翻译目标语言代码，前往 https://fanyi-api.baidu.com/doc/21 查看所有语言代码")
    public static final ConfiguredValue<String> TO_LANGUAGE = ConfiguredValue.of(String.class, "zh");
}
