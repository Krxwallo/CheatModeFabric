package io.github.krxwallo.cheatmode.mixins;

import io.github.krxwallo.cheatmode.hooks.ChatScreenHooks;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(at = @At("TAIL"), method = "init()V")
    public void init(CallbackInfo ci) {
        var screen = (ChatScreen) (Object) this;
        if (screen instanceof SleepingChatScreen) return;

        ChatScreenHooks.INSTANCE.init(screen);
    }
}