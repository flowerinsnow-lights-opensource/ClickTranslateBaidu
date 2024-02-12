package online.flowerinsnow.clicktranslatebaidu.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import online.flowerinsnow.clicktranslatebaidu.client.ClickTranslateBaiduClient;

@Environment(EnvType.CLIENT)
public class GuiConfiguration extends Screen {
    private final Screen parent;

    public GuiConfiguration(Screen parent) {
        super(Text.translatable("click-translate-baidu.configuration.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(
                this.width / 2 - 100, this.height / 2 - 20, 200, 20,
                Text.translatable("click-translate-baidu.configuration.reload.button"),
                button -> {
                    ClickTranslateBaiduClient.reloadConfig();
                    button.setMessage(Text.translatable("click-translate-baidu.configuration.reload.success")
                            .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GREEN))));
                }
        ));

        this.addDrawableChild(new ButtonWidget(
                this.width / 2 - 100, this.height / 2, 200, 20,
                Text.translatable("click-translate-baidu.configuration.done"),
                button -> MinecraftClient.getInstance().setScreen(GuiConfiguration.this.parent)
        ));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MutableText title = Text.translatable("click-translate-baidu.configuration.title");
        this.renderBackground(matrices);
        this.textRenderer.drawWithShadow(matrices, title, (float) this.width / 2 - (float) this.textRenderer.getWidth(title) / 2, 10, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
