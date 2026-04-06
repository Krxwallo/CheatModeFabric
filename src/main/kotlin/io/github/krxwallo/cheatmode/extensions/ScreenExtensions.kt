package io.github.krxwallo.cheatmode.extensions

import io.github.krxwallo.cheatmode.mixins.ScreenAccessor
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.screens.Screen

fun Screen.addDrawableChild(element: AbstractWidget) = (this as ScreenAccessor).callAddRenderableWidget(element)
