package io.github.krxwallo.cheatmode.options

import io.github.krxwallo.cheatmode.widget.OptionSliderWidget
import io.github.krxwallo.cheatmode.widget.SimpleButtonWidget
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.network.chat.Component

sealed class CheatModeOption<T>(private val title: String, var value: T, val tooltip: String?) {
    val text: Component get() = Component.translatable(title).append(": ").append(value())
    protected var onValueChange: ((T) -> Unit)? = null

    abstract fun widget(x: Int, y: Int, width: Int = 150, height: Int = 20): AbstractWidget
    open fun value() = value.toString()

    fun onChange(callback: (T) -> Unit): CheatModeOption<T> {
        onValueChange = callback
        return this
    }
}

class BooleanOption(title: String, value: Boolean, tooltip: String? = null) : CheatModeOption<Boolean>(title, value, tooltip) {
    override fun widget(x: Int, y: Int, width: Int, height: Int) = SimpleButtonWidget(x, y, width, height, text) {
        value = !value
        it.setMessage(text)
        onValueChange?.invoke(value)
    }

    override fun value() = if (value) "ON" else "OFF"
}

class DoubleOption(title: String, value: Double, private val from: (Double) -> Double, private val to: (Double) -> Double, tooltip: String? = null)
    : CheatModeOption<Double>(title, value, tooltip) {
    override fun widget(x: Int, y: Int, width: Int, height: Int) = OptionSliderWidget(this, x, y, width, height, text, to(value)) {
        value = from(it)
        onValueChange?.invoke(value)
    }

    override fun value() = String.format("%.1f", value)
}
