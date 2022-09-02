package com.justAm0dd3r.cheatmode.mixins;

import com.justAm0dd3r.cheatmode.hooks.ScreenHooks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(at = @At("TAIL"), method = "init(Lnet/minecraft/client/MinecraftClient;II)V")
    public void init(MinecraftClient mc, int i, int j, CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenInit((Screen) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "close")
    public void close(CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenClose((Screen) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenDraw((Screen) (Object) this, stack, mouseX, mouseY);
    }
}