package com.justAm0dd3r.cheatmode.utils

import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.gui.Narratable
import net.minecraft.client.gui.Selectable
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget

@Suppress("UNCHECKED_CAST")
fun Screen.addDrawable(drawable: Drawable) {
    val field = Screen::class.java.getDeclaredField("drawables")
    field.isAccessible = true
    (field[this] as MutableList<Drawable>).add(drawable)
}


@Suppress("UNCHECKED_CAST")
fun <T> Screen.addWidget(button: T) where T : Element, T : Selectable {
    val field = Screen::class.java.getDeclaredField("selectables")
    field.isAccessible = true
    (field[this] as MutableList<Narratable>).add(button)
    (children() as MutableList<Element>).add(button)
}

@Suppress("UNCHECKED_CAST")
fun Screen.addButton(button: ButtonWidget) {
    addDrawable(button)
    (children() as MutableList<Element>).add(button)
}