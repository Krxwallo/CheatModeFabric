package io.github.krxwallo.cheatmode.widget

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.AbstractButton
import net.minecraft.client.gui.narration.NarrationElementOutput
import net.minecraft.client.input.InputWithModifiers
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraft.network.chat.Component

class ItemButton(x: Int, y: Int, private val action: () -> Unit) :
    AbstractButton(x, y, 16, 16, Component.empty()) {

    private val icon = ItemStack(Blocks.GRASS_BLOCK)

    override fun onPress(input: InputWithModifiers) {
        action()
    }

    override fun extractContents(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, tickDelta: Float) {
        graphics.item(icon, x, y)
    }

    override fun updateWidgetNarration(output: NarrationElementOutput) {
        defaultButtonNarrationText(output)
    }
}
