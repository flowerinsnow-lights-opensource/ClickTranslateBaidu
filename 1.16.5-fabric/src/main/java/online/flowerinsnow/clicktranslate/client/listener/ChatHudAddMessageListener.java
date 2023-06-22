package online.flowerinsnow.clicktranslate.client.listener;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.clicktranslate.client.ClickTranslateClient;
import online.flowerinsnow.clicktranslate.client.config.Config;
import online.flowerinsnow.clicktranslate.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslate.client.util.MessageUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ChatHudAddMessageListener implements ChatHudAddMessageCallback {
    @Override
    public ActionResult onAddMessage(Text message, int messageId, int timestamp, boolean refresh) {
        if (!Config.ENABLE.getNotNull()) {
            return ActionResult.PASS;
        }
        if (message instanceof MutableText) {
            MutableText mt = (MutableText) message;
            String content = MessageUtils.removeColour(mt.getString());
            int index;
            if ((index = content.indexOf(Config.SPLIT_CHAR.getNotNull())) != -1) {
                content = content.substring(index + Config.SPLIT_CHAR.getNotNull().length());
            }
            try {
                mt.append(new LiteralText(MessageUtils.parseColour(Config.TEXT.getNotNull()))
                        .setStyle(
                                Style.EMPTY.withClickEvent(
                                        new ClickEvent(ClickEvent.Action.OPEN_URL, ClickTranslateClient.PROTOCOL + URLEncoder.encode(content, "UTF-8"))
                                ).withHoverEvent(
                                        new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("clicktranslate.command.clicktranslate.button.hover"))
                                )
                        )
                );
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException();
            }
        }
        return ActionResult.PASS;
    }
}
