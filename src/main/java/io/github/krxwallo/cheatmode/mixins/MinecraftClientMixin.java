package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.hooks.ScreenHooks;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "stop", at = @At("HEAD"))
    private void onStop(CallbackInfo ci) {
        // fix stuck in creative mode
        if (((MinecraftClient) (Object) this).currentScreen != null) {
            //noinspection DataFlowIssue
            ScreenHooks.INSTANCE.onScreenClose(((MinecraftClient) (Object) this).currentScreen);
        }
    }
}
