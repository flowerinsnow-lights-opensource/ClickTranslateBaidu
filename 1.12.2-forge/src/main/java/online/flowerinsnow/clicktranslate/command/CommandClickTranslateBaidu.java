package online.flowerinsnow.clicktranslate.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.exception.TranslateException;
import online.flowerinsnow.clicktranslate.object.TranslateResult;
import online.flowerinsnow.clicktranslate.util.TranslateUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("NullableProblems")
@SideOnly(Side.CLIENT)
public class CommandClickTranslateBaidu extends CommandBase {
    public static final String COMMAND_NAME = "clicktranslate";

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "click-translate-baidu.command.clicktranslate.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            Config.reload();
            sender.sendMessage(new TextComponentTranslation("click-translate-baidu.command.clicktranslate.reload"));
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
                    sender.sendMessage(new TextComponentTranslation("click-translate-baidu.translate.response", I18n.format("click-translate-baidu.translate.language." + result.getFrom().name), result.getDst()));
                } catch (TranslateException e) {
                    sender.sendMessage(new TextComponentTranslation("click-translate-baidu.command.error.prefix", I18n.format(e.getMessage())));
                }
            }).start();
            return;
        }
        throw new WrongUsageException(getUsage(sender));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.equals(Minecraft.getMinecraft().player);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return Stream.of("reload", "translate")
                    .filter(s -> s.toLowerCase(Locale.ROOT).startsWith(args[0].toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
