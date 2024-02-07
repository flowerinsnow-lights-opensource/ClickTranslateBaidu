package online.flowerinsnow.clicktranslate;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.command.CommandClickTranslateBaidu;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.listener.ChatListener;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = ClickTranslate.MODID,
        version = ClickTranslate.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class ClickTranslate {
    public static final String MODID = "click-translate-baidu";
    public static final String VERSION = "@VERSION@";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.init(event.getSuggestedConfigurationFile());
        logger = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandClickTranslateBaidu());
        MinecraftForge.EVENT_BUS.register(new ChatListener());
    }

    public static Logger getLogger() {
        return logger;
    }
}
