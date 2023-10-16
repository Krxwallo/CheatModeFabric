package io.github.krxwallo.cheatmode.widget

import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class SimpleButtonWidget(x: Int, y: Int, width: Int, height: Int, text: Text?, pressAction: PressAction?
) : ButtonWidget(x, y, width, height, text,
    pressAction, DEFAULT_NARRATION_SUPPLIER
)