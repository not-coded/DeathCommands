package net.notcoded.deathcommands.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.notcoded.deathcommands.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class PlayerEventsMixin {
    @Inject(method = "requestRespawn", at = @At("HEAD"))
    private void die(CallbackInfo ci) {
        if(Main.config.isEnabled){
            for(String s : Main.config.messages){
                if (s != null && s.trim().length() != 0 && Main.client.player != null) {
                    for(int i = 0; i < Main.config.amountTimes; i++){
                        Main.client.player.sendChatMessage(s);
                    }
                }
            }
        }
    }
}
