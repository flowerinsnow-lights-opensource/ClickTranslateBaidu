package online.flowerinsnow.clicktranslate.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.clicktranslate.client.eci.ChatHudAddMessageCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
@Environment(EnvType.CLIENT)
public class MixinChatHud {
    @Inject(
            method = "addMessage(Lnet/minecraft/text/Text;IIZ)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void addMessage(Text message, int messageId, int timestamp, boolean refresh, CallbackInfo ci) {
        if (ChatHudAddMessageCallback.EVENT.invoker().onAddMessage(message, messageId, timestamp, refresh) == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
