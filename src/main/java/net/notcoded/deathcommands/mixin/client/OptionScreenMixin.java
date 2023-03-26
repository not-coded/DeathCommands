package net.notcoded.deathcommands.mixin.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.notcoded.deathcommands.Main;
import net.notcoded.deathcommands.config.ClientModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
@Environment(EnvType.CLIENT)
public abstract class OptionScreenMixin extends Screen {


    protected OptionScreenMixin(Component component) {
        super(component);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addButton(CallbackInfo ci) {
        if(Main.clientModConfig.optionsMenu){
            this.addRenderableWidget(new Button(this.width / 2 + 5, this.height / 6 - 12 + 30, 150, 20, Component.translatable("deathcommands.options"), (buttonWidget) -> Main.client.setScreen(AutoConfig.getConfigScreen(ClientModConfig.class, this).get())));
        }
    }
}
