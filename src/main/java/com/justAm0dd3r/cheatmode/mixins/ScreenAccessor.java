package com.justAm0dd3r.cheatmode.mixins;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Screen.class)
public interface ScreenAccessor {
    @Accessor
    List<Selectable> getSelectables();
    @Accessor
    List<Drawable> getDrawables();
    @Accessor
    List<Element> getChildren();
}
