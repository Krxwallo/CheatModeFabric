package com.justAm0dd3r.cheatmode.mixins;

import com.justAm0dd3r.cheatmode.ManagerKt;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class ChatMixin {
    @Inject(at = @At("HEAD"), method = "onGameMessage", cancellable = true)
    public void handleSystemMessage(Text msg, boolean b, CallbackInfo ci) {
        if (msg.getContent() instanceof TranslatableTextContent contents) {
            if (!ManagerKt.isSingleplayer()) if (contents.getKey().equals("commands.gamemode.success.self")) ci.cancel();
        }
    }
}
