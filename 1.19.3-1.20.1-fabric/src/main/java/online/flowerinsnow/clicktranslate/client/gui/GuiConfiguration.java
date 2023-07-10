package online.flowerinsnow.clicktranslate.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import online.flowerinsnow.clicktranslate.client.ClickTranslateClient;

@Environment(EnvType.CLIENT)
public class GuiConfiguration extends Screen {
    private final Screen parent;

    public GuiConfiguration(Screen parent) {
        super(Text.translatable("clicktranslate.configuration.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("clicktranslate.configuration.reload.button"),
                button -> {
                    ClickTranslateClient.reloadConfig();
                    button.setMessage(Text.translatable("clicktranslate.configuration.reload.success")
                            .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GREEN)))
                    );
                }
        ).dimensions(this.width / 2 - 100, this.height / 2 - 20, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("clicktranslate.configuration.done"),
                button -> MinecraftClient.getInstance().setScreen(GuiConfiguration.this.parent)
        ).dimensions(this.width / 2 - 100, this.height / 2, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MutableText title = Text.translatable("clicktranslate.configuration.title");
        this.renderBackground(context);
        context.drawTextWithShadow(this.textRenderer, title, this.width / 2 - this.textRenderer.getWidth(title) / 2, 10, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
