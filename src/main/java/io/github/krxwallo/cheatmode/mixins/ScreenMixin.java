package io.github.krxwallo.cheatmode.mixins;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import io.github.krxwallo.cheatmode.hooks.ScreenHooks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.navigation.GuiNavigationPath;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
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
    public void render(DrawContext drawContext, int mouseX, int mouseY, float f, CallbackInfo ci) {
        ScreenHooks.INSTANCE.onScreenDraw((Screen) (Object) this, drawContext, mouseX, mouseY);
    }

    @WrapWithCondition(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;switchFocus(Lnet/minecraft/client/gui/navigation/GuiNavigationPath;)V"), method = "keyPressed")
    private boolean switchFocus(Screen instance, GuiNavigationPath guiNavigationPath) {
        // Fix arrow keys not working correctly in chat screen
        //noinspection ConstantValue
        return !(((Object) this) instanceof ChatScreen);
    }
}