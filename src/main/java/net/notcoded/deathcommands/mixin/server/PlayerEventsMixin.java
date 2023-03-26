package net.notcoded.deathcommands.mixin.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.notcoded.deathcommands.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayer.class)
public class PlayerEventsMixin {

    @Inject(method = "die", at = @At("HEAD"))
    private void die(DamageSource damageSource, CallbackInfo ci) {
        if(Main.serverModConfig.isEnabled){
            for(String s : Main.serverModConfig.server_messages){
                if (s != null && s.trim().length() != 0 && Main.server != null) {
                    for(int i = 0; i < Main.serverModConfig.server_amountTimes; i++){
                        String replacedS = s;
                        if(s.startsWith("/")){
                            replacedS = s.replaceFirst("/", "");
                        }
                        Main.server.getCommands().performCommand(Main.server.createCommandSourceStack(), replacedS);
                    }
                }
            }
            for(String s : Main.serverModConfig.player_messages){
                if (s != null && s.trim().length() != 0 && Main.server != null) {
                    for(int i = 0; i < Main.serverModConfig.player_amountTimes; i++){
                        ServerPlayer player = (ServerPlayer) (Object) this;
                        if(s.startsWith("/")){
                            String replacedS = s.replaceFirst("/", "");
                            Main.server.getCommands().performCommand(player.createCommandSourceStack(), replacedS);
                        } else {
                            player.sendChatMessage(new PlayerChatMessage(Component.literal(s), MessageSignature.unsigned(), Optional.empty()), player.asChatSender(), ChatType.CHAT);
                        }
                    }
                }
            }
        }
    }
}
