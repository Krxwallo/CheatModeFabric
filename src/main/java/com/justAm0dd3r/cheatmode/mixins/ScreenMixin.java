package com.justAm0dd3r.cheatmode.mixins;

import com.justAm0dd3r.cheatmode.Hooks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(at = @At("TAIL"), method = "init(Lnet/minecraft/client/MinecraftClient;II)V")
    public void init(MinecraftClient mc, int i, int j, CallbackInfo ci) {
        Hooks.INSTANCE.onScreenInit((Screen) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "close")
    public void close(CallbackInfo ci) {
        Hooks.INSTANCE.onScreenClose((Screen) (Object) this);
    }
}