package io.github.krxwallo.cheatmode.mixins;

import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractParentElement.class)
public class AbstractParentElementMixin {
    // Fix sliders unfocused
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Element;setFocused(Z)V"), method = "setFocused")
    public void render(Element instance, boolean b) {
        if (instance instanceof SleepingChatScreen) return;
        if (instance instanceof ChatScreen screen) {
            if (!screen.isDragging()) screen.setFocused(b);
        }
    }
}
