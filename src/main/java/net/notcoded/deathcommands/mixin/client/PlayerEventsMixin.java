package net.notcoded.deathcommands.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.notcoded.deathcommands.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class PlayerEventsMixin {
    @Inject(method = "respawn", at = @At("HEAD"))
    private void die(CallbackInfo ci) {
        if(Main.clientModConfig.isEnabled){
            for(String s : Main.clientModConfig.messages){
                if (s != null && s.trim().length() != 0 && Main.client.player != null) {
                    for(int i = 0; i < Main.clientModConfig.amountTimes; i++){
                        if(s.startsWith("/")){
                            String replacedS = s.replaceFirst("/", "");
                            Main.client.player.command(replacedS, Component.literal(replacedS));
                        } else {
                            Main.client.player.chat(s, Component.literal(s));
                        }

                    }
                }
            }
        }
    }
}
