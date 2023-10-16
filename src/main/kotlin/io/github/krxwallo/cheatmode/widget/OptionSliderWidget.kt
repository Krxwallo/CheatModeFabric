package io.github.krxwallo.cheatmode.widget

import io.github.krxwallo.cheatmode.options.CheatModeOption
import net.minecraft.client.gui.widget.SliderWidget
import net.minecraft.text.Text

class OptionSliderWidget(private val option: CheatModeOption<Double>, x: Int, y: Int, width: Int, height: Int, text: Text, value: Double, private val callback: ((Double) -> Unit)?)
    : SliderWidget(x, y, width, height, text, value) {
    override fun updateMessage() {
        message = option.text
    }

    override fun applyValue() {
        callback?.invoke(value)
    }
}