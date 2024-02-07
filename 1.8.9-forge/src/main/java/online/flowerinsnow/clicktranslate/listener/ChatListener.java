package online.flowerinsnow.clicktranslate.listener;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.util.MessageUtils;

@SideOnly(Side.CLIENT)
public final class ChatListener {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceive(ClientChatReceivedEvent event) {
        if (!Config.propertyEnable.getBoolean()) {
            return;
        }
        if (event.type == 0) { // Standard Text Message
            String message = MessageUtils.removeColour(event.message.getFormattedText());
            String splitChar = Config.propertySplitChar.getString();
            int index;
            if ((index = message.indexOf(splitChar)) != -1) {
                message = message.substring(index + splitChar.length());
            }
            ChatComponentText component = new ChatComponentText(MessageUtils.parseColour(Config.propertyText.getString()));
            component.setChatStyle(new ChatStyle()
                    .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clicktranslate translate " + message))
                    .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentTranslation("click-translate-baidu.command.clicktranslate.button.hover")))
            );
            event.message.appendSibling(component);
        }
    }
}
