package online.flowerinsnow.clicktranslate.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.clicktranslate.client.eci.ClientSendCommandCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
@Environment(EnvType.CLIENT)
public class MixinClientPlayNetworkHandler {
    @Inject(
            method = "sendCommand",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V",
                    ordinal = 0
            ),
            cancellable = true
    )
    public void sendCommand(String command, CallbackInfoReturnable<Boolean> cir) {
        if (ClientSendCommandCallback.EVENT.invoker().onClientSendCommand(command) == ActionResult.FAIL) {
            cir.cancel();
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "sendChatCommand",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V",
                    ordinal = 0
            ),
            cancellable = true
    )
    public void sendChatCommand(String command, CallbackInfo ci) {
        if (ClientSendCommandCallback.EVENT.invoker().onClientSendCommand(command) == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
