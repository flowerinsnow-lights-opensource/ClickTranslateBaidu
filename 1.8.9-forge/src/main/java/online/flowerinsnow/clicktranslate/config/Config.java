package online.flowerinsnow.clicktranslate.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public abstract class Config {
    private Config() {
    }

    private static Configuration configuration;
    public static Property propertyEnable;
    public static Property propertyText;
    public static Property propertySplitChar;
    public static Property propertyAPI;
    public static Property propertyAppID;
    public static Property propertyAppSecret;
    public static Property propertyToLanguage;

    public static void init(File file) {
        configuration = new Configuration(file);
        reload();
        save();
    }

    public static void reload() {
        configuration.load();
        final String categoryGeneral = "general";
        propertyEnable = configuration.get(categoryGeneral, "enable", true, "Enable or not");
        propertyText = configuration.get(categoryGeneral, "text", "&a [T]", "Text of translate button");
        propertySplitChar = configuration.get(categoryGeneral, "split_char", ":", "Split char, substring message from the first split char");
        propertyAPI = configuration.get(categoryGeneral, "api", "https://fanyi-api.baidu.com/api/trans/vip/translate", "API Address");
        propertyAppID = configuration.get(categoryGeneral, "app_id", "unfilled", "Obtain from https://fanyi-api.baidu.com/manage/developer . If it is a pure number, you need to add English double quotes");
        propertyAppSecret = configuration.get(categoryGeneral, "app_secret", "unfilled", "Obtain from https://fanyi-api.baidu.com/manage/developer .");
        propertyToLanguage = configuration.get(categoryGeneral, "to_language", "zh", "Code of target language，View full codes from https://fanyi-api.baidu.com/doc/21 .");
    }

    public static void save() {
        configuration.save();
    }
}
