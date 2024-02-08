package online.flowerinsnow.clicktranslate.listener;

import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.command.CommandClickTranslateBaidu;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.util.MessageUtils;

@SideOnly(Side.CLIENT)
public final class ChatListener {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceive(ClientChatReceivedEvent event) {
        if (!Config.propertyEnable.getBoolean()) {
            return;
        }
        if (event.getType() == ChatType.CHAT) { // Standard Text Message
            String message = MessageUtils.removeColour(event.getMessage().getFormattedText());
            String splitChar = Config.propertySplitChar.getString();
            int index;
            if ((index = message.indexOf(splitChar)) != -1) {
                message = message.substring(index + splitChar.length());
            }
            TextComponentString component = new TextComponentString(MessageUtils.parseColour(Config.propertyText.getString()));
            component.setStyle(new Style()
                    .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + CommandClickTranslateBaidu.COMMAND_NAME + " translate " + message))
                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("click-translate-baidu.command.clicktranslate.button.hover")))
            );
            event.getMessage().appendSibling(component);
        }
    }
}
