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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CommandClickTranslate extends CommandBase {
    @Override
    public @NotNull String getName() {
        return "clicktranslate";
    }

    @Override
    public @NotNull String getUsage(@NotNull ICommandSender sender) {
        return "clicktranslate.command.clicktranslate.usage";
    }

    @Override
    public void execute(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            Config.reload();
            sender.sendMessage(new TextComponentTranslation("clicktranslate.command.clicktranslate.reload"));
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
                    sender.sendMessage(new TextComponentTranslation("clicktranslate.translate.response", I18n.format("clicktranslate.translate.language." + result.getFrom().name), result.getDst()));
                } catch (TranslateException e) {
                    sender.sendMessage(new TextComponentTranslation("clicktranslate.command.error.prefix", I18n.format(e.getMessage())));
                }
            }).start();
            return;
        }
        throw new WrongUsageException(getUsage(sender));
    }

    @Override
    public boolean checkPermission(@NotNull MinecraftServer server, ICommandSender sender) {
        return sender.equals(Minecraft.getMinecraft().player);
    }

    @Override
    public @NotNull List<String> getTabCompletions(@NotNull MinecraftServer server, @NotNull ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>(Arrays.asList("reload", "translate"));
            subCommands.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return subCommands;
        }
        return new ArrayList<>();
    }
}
