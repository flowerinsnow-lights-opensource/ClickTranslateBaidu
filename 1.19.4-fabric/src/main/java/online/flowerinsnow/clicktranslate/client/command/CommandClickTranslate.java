package online.flowerinsnow.clicktranslate.client.command;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.clicktranslate.client.ClickTranslateClient;
import online.flowerinsnow.clicktranslate.client.eci.ClientSendCommandCallback;
import online.flowerinsnow.clicktranslate.client.exception.TranslateException;
import online.flowerinsnow.clicktranslate.client.object.TranslateResult;
import online.flowerinsnow.clicktranslate.client.util.TranslateUtils;

import java.util.regex.Pattern;

@Environment(EnvType.CLIENT)
public class CommandClickTranslate implements ClientSendCommandCallback {
    @Override
    public ActionResult onClientSendCommand(String command) {
        if (Pattern.compile("clicktranslate( .*)?").matcher(command).matches()) {
            String[] split = command.split(" ");
            String[] args = new String[split.length - 1];
            System.arraycopy(split, 1, args, 0, args.length);
            execute(args);
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    public void execute(String[] args) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) {
            return;
        }
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            ClickTranslateClient.reloadConfig();
            player.sendMessage(Text.translatable("clicktranslate.command.clicktranslate.reload"));
            return;
        } else if (args.length > 1 && "translate".equalsIgnoreCase(args[0])) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]);
                if (i + 1 < args.length) {
                    sb.append(" ");
                }
            }
            new Thread(() -> {
                try {
                    TranslateResult result = TranslateUtils.translate(sb.toString());
                    player.sendMessage(Text.translatable("clicktranslate.translate.response", I18n.translate("clicktranslate.translate.language." + result.getFrom().name), result.getDst()));
                } catch (TranslateException e) {
                    player.sendMessage(Text.translatable("clicktranslate.command.error.prefix", I18n.translate(e.getMessage())));
                }
            }).start();
            return;
        }
        player.sendMessage(Text.translatable("clicktranslate.command.clicktranslate.usage"));
    }
}
