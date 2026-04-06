package io.github.krxwallo.cheatmode.widget

import net.minecraft.client.gui.components.Button
import net.minecraft.network.chat.Component

fun SimpleButtonWidget(x: Int, y: Int, width: Int, height: Int, text: Component, pressAction: Button.OnPress): Button =
    Button.builder(text, pressAction)
        .bounds(x, y, width, height)
        .build()
