package online.flowerinsnow.clicktranslate.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
