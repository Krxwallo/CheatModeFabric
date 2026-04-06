package io.github.krxwallo.cheatmode.widget

import net.minecraft.client.gui.components.Button
import net.minecraft.network.chat.Component

fun ItemButton(x: Int, y: Int, action: Button.OnPress): Button =
    SimpleButtonWidget(x, y, 16, 16, Component.literal("C"), action)
