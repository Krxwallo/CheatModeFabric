package io.github.krxwallo.cheatmode.widget

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.AbstractButton
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.client.gui.narration.NarrationElementOutput
import net.minecraft.client.input.InputWithModifiers
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks

class ItemButton(x: Int, y: Int, private val action: () -> Unit) :
    AbstractButton(x, y, 16, 16, Component.translatable("gui.cheatmode.open_creative_inventory")) {

    private val icon = ItemStack(Blocks.GRASS_BLOCK)

    init {
        setTooltip(Tooltip.create(Component.translatable("gui.cheatmode.open_creative_inventory")))
    }

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
