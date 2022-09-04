package com.justAm0dd3r.cheatmode.hooks

import com.justAm0dd3r.cheatmode.extensions.addDrawableChild
import com.justAm0dd3r.cheatmode.options.options
import net.minecraft.client.gui.screen.ChatScreen

object ChatScreenHooks {
    fun init(screen: ChatScreen) {
        val width = screen.width
        val height = screen.height

        options.forEachIndexed { i, option ->
            val x = width / 5 * 3
            val y = height / 10 - 12 + 24 * i
            val button = option.widget(x, y)
            screen.addDrawableChild(button)
        }
    }
}