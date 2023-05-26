package online.flowerinsnow.clicktranslate.client.eci;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

/**
 * 返回FAIL时取消事件
 */
public interface ClientSendCommandCallback {
    Event<ClientSendCommandCallback> EVENT = EventFactory.createArrayBacked(ClientSendCommandCallback.class,
            (listeners) -> (command) -> {
                for (ClientSendCommandCallback listener : listeners) {
                    ActionResult result = listener.onClientSendCommand(command);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
    });

    ActionResult onClientSendCommand(String command);
}
