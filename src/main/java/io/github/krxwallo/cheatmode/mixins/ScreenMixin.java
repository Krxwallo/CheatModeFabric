package io.github.krxwallo.cheatmode.mixins;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import io.github.krxwallo.cheatmode.hooks.ScreenHooks;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(at = @At("TAIL"), method = "init()V")
    public void init(CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenInit((Screen) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "onClose()V")
    public void close(CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenClose((Screen) (Object) this);
    }

    @WrapWithCondition(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;changeFocus(Lnet/minecraft/client/gui/ComponentPath;)V"), method = "keyPressed")
    private boolean switchFocus(Screen instance, ComponentPath componentPath) {
        // Fix arrow keys not working correctly in chat screen
        //noinspection ConstantValue
        return !(((Object) this) instanceof ChatScreen);
    }
}
