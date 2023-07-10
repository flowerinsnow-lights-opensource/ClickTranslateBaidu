package online.flowerinsnow.clicktranslate.client.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import online.flowerinsnow.clicktranslate.client.gui.GuiConfiguration;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<GuiConfiguration> getModConfigScreenFactory() {
        return GuiConfiguration::new;
    }
}
