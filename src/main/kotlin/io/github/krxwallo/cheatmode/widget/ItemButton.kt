package io.github.krxwallo.cheatmode.widget

import net.minecraft.block.Blocks
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class ItemButton(x: Int, y: Int, action: PressAction) : ButtonWidget(x, y, 16, 16, Text.empty(), action, DEFAULT_NARRATION_SUPPLIER) {
    override fun renderButton(drawContext: DrawContext?, i: Int, j: Int, f: Float) {
        //mc.itemRenderer
        alpha = 200.0f

        val stack = ItemStack(Blocks.GRASS_BLOCK)

        drawContext?.drawItem(stack, x, y)

        alpha = 0f
/*

        renderer.renderItem(stack, ModelTransformationMode.GUI, false, drawContext?.matrices, drawContext?.vertexConsumers, x, y)

        renderer.zOffset = 200.0f
        renderer.renderInGuiWithOverrides(ItemStack(Blocks.GRASS_BLOCK), x, y)
        alpha = 0.0f
        renderer.zOffset = 0.0f
*/

        super.renderButton(drawContext, i, j, f)
    }
}