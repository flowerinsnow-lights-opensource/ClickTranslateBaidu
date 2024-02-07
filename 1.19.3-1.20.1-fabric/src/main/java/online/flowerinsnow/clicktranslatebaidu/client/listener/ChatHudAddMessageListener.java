package online.flowerinsnow.clicktranslatebaidu.client.listener;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.clicktranslatebaidu.client.ClickTranslateBaiduClient;
import online.flowerinsnow.clicktranslatebaidu.client.config.Config;
import online.flowerinsnow.clicktranslatebaidu.client.eci.ChatHudAddMessageCallback;
import online.flowerinsnow.clicktranslatebaidu.client.util.MessageUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Environment(EnvType.CLIENT)
public class ChatHudAddMessageListener implements ChatHudAddMessageCallback {
    @Override
    public ActionResult onAddMessage(Text message, MessageSignatureData signature, MessageIndicator indicator) {
        if (!Config.ENABLE.getNotNull()) {
            return ActionResult.PASS;
        }
        if (message instanceof MutableText mt) {
            String content = MessageUtils.removeColour(mt.getString());
            int index;
            if ((index = content.indexOf(Config.SPLIT_CHAR.getNotNull())) != -1) {
                content = content.substring(index + Config.SPLIT_CHAR.getNotNull().length());
            }
            mt.append(Text.literal(MessageUtils.parseColour(Config.TEXT.getNotNull()))
                    .setStyle(
                            Style.EMPTY.withClickEvent(
                                    new ClickEvent(ClickEvent.Action.OPEN_URL, ClickTranslateBaiduClient.PROTOCOL + URLEncoder.encode(content, StandardCharsets.UTF_8))
                            ).withHoverEvent(
                                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("click-translate-baidu.command.clicktranslate.button.hover"))
                            )
                    )
            );
        }
        return ActionResult.PASS;
    }
}
