package com.justAm0dd3r.cheatmode.hooks

import com.justAm0dd3r.cheatmode.mc
import com.justAm0dd3r.cheatmode.options.CheatModeOptions.flight
import com.justAm0dd3r.cheatmode.options.CheatModeOptions.instantCreativeInventory
import com.justAm0dd3r.cheatmode.options.CheatModeOptions.reach
import com.justAm0dd3r.cheatmode.utils.addDrawable
import com.justAm0dd3r.cheatmode.utils.addWidget
import net.minecraft.client.gui.screen.ChatScreen
import net.minecraft.client.option.SimpleOption

object ChatScreenHooks {
    fun init(screen: ChatScreen) {
        val width = screen.width
        val height = screen.height

        arrayOf<SimpleOption<*>>(
            instantCreativeInventory,
            flight,
            reach
        ).forEachIndexed { i, option ->
            val j = width / 5 * 3
            val k = height / 10 - 12 + 24 * i
            val button = option.createButton(mc.options, j, k, 150)
            screen.addDrawable(button)
            screen.addWidget(button)
        }
    }
}