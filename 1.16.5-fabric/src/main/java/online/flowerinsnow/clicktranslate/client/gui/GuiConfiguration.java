package online.flowerinsnow.clicktranslate.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import online.flowerinsnow.clicktranslate.client.ClickTranslateClient;

@Environment(EnvType.CLIENT)
public class GuiConfiguration extends Screen {
    private final Screen parent;

    public GuiConfiguration(Screen parent) {
        super(new TranslatableText("clicktranslate.configuration.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addButton(new ButtonWidget(
                this.width / 2 - 100, this.height / 2 - 20, 200, 20,
                new TranslatableText("clicktranslate.configuration.reload.button"),
                button -> {
                    ClickTranslateClient.reloadConfig();
                    button.setMessage(new TranslatableText("clicktranslate.configuration.reload.success")
                            .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GREEN)))
                    );
                }
        ));

        this.addButton(new ButtonWidget(
                this.width / 2 - 100, this.height / 2, 200, 20,
                new TranslatableText("clicktranslate.configuration.done"),
                button -> MinecraftClient.getInstance().openScreen(GuiConfiguration.this.parent)
        ));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        TranslatableText title = new TranslatableText("clicktranslate.configuration.title");
        this.renderBackground(matrices);
        this.textRenderer.drawWithShadow(matrices, title, (float) (this.width / 2 - this.textRenderer.getWidth(title) / 2), 10.0F, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
