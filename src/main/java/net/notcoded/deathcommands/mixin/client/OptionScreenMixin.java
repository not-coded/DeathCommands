package net.notcoded.deathcommands.mixin.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.notcoded.deathcommands.Main;
import net.notcoded.deathcommands.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
@Environment(EnvType.CLIENT)
public abstract class OptionScreenMixin extends Screen {

    protected OptionScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addCustomButton(CallbackInfo ci) {
        if(Main.config.optionsMenu && Main.config.isEnabled){
            this.addButton(new ButtonWidget(this.width / 2 + 5, this.height / 6 - 12 + 30, 150, 20, new TranslatableText("deathcommands.options"), (buttonWidget) -> {
                Main.client.openScreen(AutoConfig.getConfigScreen(ModConfig.class, this).get());
            }));
        }
    }
}
