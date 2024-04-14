package online.flowerinsnow.clicktranslatebaidu.client.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import online.flowerinsnow.clicktranslatebaidu.client.gui.GuiConfiguration;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<GuiConfiguration> getModConfigScreenFactory() {
        return GuiConfiguration::new;
    }
}
