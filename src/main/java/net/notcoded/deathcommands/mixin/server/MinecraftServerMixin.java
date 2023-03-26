package net.notcoded.deathcommands.mixin.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.notcoded.deathcommands.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.SERVER)
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    boolean firstTickPassed = false;

    @Inject(at = @At("HEAD"), method = "tickChildren")
    private void tickHead(CallbackInfo ci) {
        if (!firstTickPassed) {
            firstTickPassed = true;
            Main.server = (MinecraftServer) (Object) this;
        }
    }
}
