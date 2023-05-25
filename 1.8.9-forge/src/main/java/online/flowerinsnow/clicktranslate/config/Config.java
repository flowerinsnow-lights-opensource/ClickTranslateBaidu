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
        propertyEnable = configuration.get(categoryGeneral, "enable", true, "是否启用功能");
        propertyText = configuration.get(categoryGeneral, "text", "&a [翻译]", "显示的按钮");
        propertySplitChar = configuration.get(categoryGeneral, "split_char", ":", "分割符，截取第一个分隔符后面的文本进行翻译");
        propertyAPI = configuration.get(categoryGeneral, "api", "https://fanyi-api.baidu.com/api/trans/vip/translate", "API地址，不懂不要修改");
        propertyAppID = configuration.get(categoryGeneral, "app_id", "未填写", "前往 https://fanyi-api.baidu.com/manage/developer 获取");
        propertyAppSecret = configuration.get(categoryGeneral, "app_secret", "未填写", "前往 https://fanyi-api.baidu.com/manage/developer 获取");
        propertyToLanguage = configuration.get(categoryGeneral, "to_language", "zh", "翻译目标语言代码，前往 https://fanyi-api.baidu.com/doc/21 查看所有语言代码");
    }

    public static void save() {
        configuration.save();
    }
}
