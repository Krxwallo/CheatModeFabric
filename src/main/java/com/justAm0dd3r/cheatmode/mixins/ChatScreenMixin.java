package com.justAm0dd3r.cheatmode.mixins;

import com.justAm0dd3r.cheatmode.hooks.ChatScreenHooks;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(at = @At("TAIL"), method = "init()V")
    public void init(CallbackInfo ci) {
        var screen = (ChatScreen) (Object) this;
        if (screen instanceof SleepingChatScreen) return;

        ChatScreenHooks.INSTANCE.init(screen);
    }

    // Fix sliders unfocused
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;setFocused(Lnet/minecraft/client/gui/Element;)V"), method = "render")
    public void render(ChatScreen screen, Element input) {
        if (screen instanceof SleepingChatScreen) return;

        if (!screen.isDragging()) screen.setFocused(input);
    }
}