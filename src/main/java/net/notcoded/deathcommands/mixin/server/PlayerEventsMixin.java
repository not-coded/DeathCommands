package net.notcoded.deathcommands.mixin.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.notcoded.deathcommands.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayerEntity.class)
public class PlayerEventsMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void die(DamageSource source, CallbackInfo ci) {
        if(Main.serverModConfig.isEnabled){
            for(String s : Main.serverModConfig.server_messages){
                if (s != null && s.trim().length() != 0 && Main.server != null) {
                    for(int i = 0; i < Main.serverModConfig.server_amountTimes; i++){
                        String replacedS = s;
                        if(s.startsWith("/")){
                            replacedS = s.replaceFirst("/", "");
                        }
                        Main.server.getCommandManager().execute(Main.server.getCommandSource(), replacedS);
                    }
                }
            }
            for(String s : Main.serverModConfig.player_messages){
                if (s != null && s.trim().length() != 0 && Main.server != null) {
                    for(int i = 0; i < Main.serverModConfig.player_amountTimes; i++){
                        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
                        String replacedS = s;
                        if(s.startsWith("/")){
                            replacedS = s.replaceFirst("/", "");
                        }
                        Main.server.getCommandManager().execute(player.getCommandSource(), replacedS);
                    }
                }
            }
        }
    }
}
