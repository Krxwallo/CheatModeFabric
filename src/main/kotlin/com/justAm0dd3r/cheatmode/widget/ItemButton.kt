package com.justAm0dd3r.cheatmode.widget

import com.justAm0dd3r.cheatmode.mc
import net.minecraft.block.Blocks
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class ItemButton(x: Int, y: Int, action: PressAction) : ButtonWidget(x, y, 16, 16, Text.empty(), action) {
    override fun renderButton(matrixStack: MatrixStack?, i: Int, j: Int, f: Float) {
        val renderer = mc.itemRenderer
        alpha = 200.0f
        renderer.zOffset = 200.0f
        renderer.renderInGuiWithOverrides(ItemStack(Blocks.GRASS_BLOCK), x, y)
        alpha = 0.0f
        renderer.zOffset = 0.0f

        super.renderButton(matrixStack, i, j, f)
    }
}