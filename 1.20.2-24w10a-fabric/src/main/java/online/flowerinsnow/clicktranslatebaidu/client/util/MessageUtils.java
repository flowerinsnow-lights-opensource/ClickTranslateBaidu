package online.flowerinsnow.clicktranslatebaidu.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public abstract class MessageUtils {
    private MessageUtils() {
    }

    public static String parseColour(String message) {
        return message.replace("&", "§").replace("§§", "&");
    }

    public static String removeColour(String message) {
        return message.replaceAll("§.", "");
    }
}
