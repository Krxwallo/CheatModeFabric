package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.hooks.InteractionManagerHooks;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class InteractionManagerMixin {
    @Inject(at = @At("HEAD"), method = "setGameMode")
    public void setLocalMode(GameMode gameMode, CallbackInfo ci) {
        InteractionManagerHooks.INSTANCE.setMode(gameMode);
    }

    // Render survival hearts when "cheat mode screen" is open
    @Inject(at = @At("HEAD"), method = "hasStatusBars", cancellable = true)
    public void hasStatusBars(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(InteractionManagerHooks.INSTANCE.hasBars());
    }

    // Render experience bar when "cheat mode screen" is open
    @Inject(at = @At("HEAD"), method = "hasExperienceBar", cancellable = true)
    public void hasExperience(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(InteractionManagerHooks.INSTANCE.hasBars());
    }

    @Inject(at = @At("HEAD"), method = "getReachDistance", cancellable = true)
    public void getReachDistance(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(InteractionManagerHooks.INSTANCE.getReach());
    }
}
