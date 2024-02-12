package online.flowerinsnow.clicktranslatebaidu.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import online.flowerinsnow.clicktranslatebaidu.client.ClickTranslateBaiduClient;
import online.flowerinsnow.clicktranslatebaidu.client.exception.TranslateException;
import online.flowerinsnow.clicktranslatebaidu.client.object.TranslateResult;
import online.flowerinsnow.clicktranslatebaidu.client.util.TranslateUtils;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Mixin(Screen.class)
@Environment(EnvType.CLIENT)
public class MixinScreen {
    @Inject(
            method = "handleTextClick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z",
                    opcode = Opcodes.INVOKEVIRTUAL,
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    public void onCheckProtocol(Style style, CallbackInfoReturnable<Boolean> cir, ClickEvent clickEvent, URI uRI, String string) {
        if (clickEvent.getValue().startsWith(ClickTranslateBaiduClient.PROTOCOL)) {
            new Thread(() -> {
                Text textToSend;
                try {
                    TranslateResult result = TranslateUtils.translate(URLDecoder.decode(clickEvent.getValue().substring(ClickTranslateBaiduClient.PROTOCOL.length()), StandardCharsets.UTF_8));
                    textToSend = Text.translatable("click-translate-baidu.translate.response", I18n.translate("click-translate-baidu.translate.language." + result.getFrom().name), result.getDst());
                } catch (TranslateException e) {
                    textToSend = Text.translatable("click-translate-baidu.command.error.prefix", I18n.translate(e.getMessage()));
                }
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null) {
                    player.sendMessage(textToSend, false);
                }
            }).start();
            cir.setReturnValue(true);
        }
    }
}
