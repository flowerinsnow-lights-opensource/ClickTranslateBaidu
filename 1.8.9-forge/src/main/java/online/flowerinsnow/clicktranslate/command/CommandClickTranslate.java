package online.flowerinsnow.clicktranslate.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.exception.TranslateException;
import online.flowerinsnow.clicktranslate.object.TranslateResult;
import online.flowerinsnow.clicktranslate.util.TranslateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CommandClickTranslate extends CommandBase {
    @Override
    public String getCommandName() {
        return "clicktranslate";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "clicktranslate.command.clicktranslate.usage";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.equals(Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            Config.reload();
            sender.addChatMessage(new ChatComponentTranslation("clicktranslate.command.clicktranslate.reload"));
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
                    sender.addChatMessage(new ChatComponentTranslation("clicktranslate.translate.response", I18n.format("clicktranslate.translate.language." + result.getFrom().name), result.getDst()));
                } catch (TranslateException e) {
                    sender.addChatMessage(new ChatComponentTranslation("clicktranslate.command.error.prefix", I18n.format(e.getMessage())));
                }
            }).start();
            return;
        }
        throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>(Arrays.asList("reload", "translate"));
            subCommands.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return subCommands;
        }
        return new ArrayList<>();
    }
}
