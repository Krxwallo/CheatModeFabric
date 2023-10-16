package io.github.krxwallo.cheatmode.extensions

import io.github.krxwallo.cheatmode.mixins.ScreenAccessor
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.gui.Selectable
import net.minecraft.client.gui.screen.Screen

fun Screen.addDrawableChild(element: Element) {
    this as ScreenAccessor

    drawables.add(element as Drawable)
    selectables.add(element as Selectable)
    children.add(element)
}