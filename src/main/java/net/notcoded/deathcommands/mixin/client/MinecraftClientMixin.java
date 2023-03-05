package net.notcoded.deathcommands.mixin.client;


import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.notcoded.deathcommands.Main;
import net.notcoded.deathcommands.config.ClientModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci){
        while (Main.keyBinding.wasPressed()) {
            Main.client.setScreen(AutoConfig.getConfigScreen(ClientModConfig.class, null).get());
        }
    }
}
