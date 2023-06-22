package online.flowerinsnow.clicktranslate.client.eci;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

/**
 * 返回FAIL时取消事件
 */
public interface ChatHudAddMessageCallback {
    Event<ChatHudAddMessageCallback> EVENT = EventFactory.createArrayBacked(ChatHudAddMessageCallback.class,
            (listeners) -> ((message, messageId, timestamp, refresh) -> {
                for (ChatHudAddMessageCallback listener : listeners) {
                    ActionResult result = listener.onAddMessage(message, messageId, timestamp, refresh);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }));

    ActionResult onAddMessage(Text message, int messageId, int timestamp, boolean refresh);
}
