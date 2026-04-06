package io.github.krxwallo.cheatmode.widget

import io.github.krxwallo.cheatmode.options.CheatModeOption
import net.minecraft.client.gui.components.AbstractSliderButton
import net.minecraft.network.chat.Component

class OptionSliderWidget(private val option: CheatModeOption<Double>, x: Int, y: Int, width: Int, height: Int, text: Component, value: Double, private val callback: ((Double) -> Unit)?)
    : AbstractSliderButton(x, y, width, height, text, value) {
    override fun updateMessage() {
        setMessage(option.text)
    }

    override fun applyValue() {
        callback?.invoke(value)
    }
}
