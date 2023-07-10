package online.flowerinsnow.clicktranslate.client.util;

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
