package online.flowerinsnow.clicktranslate.client.eci;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

/**
 * 返回FAIL时取消事件
 */
public interface ChatHudAddMessageCallback {
    Event<ChatHudAddMessageCallback> EVENT = EventFactory.createArrayBacked(ChatHudAddMessageCallback.class,
            (listeners) -> ((message, signature, indicator) -> {
                for (ChatHudAddMessageCallback listener : listeners) {
                    ActionResult result = listener.onAddMessage(message, signature, indicator);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }));

    ActionResult onAddMessage(Text message, MessageSignatureData signature, MessageIndicator indicator);
}
