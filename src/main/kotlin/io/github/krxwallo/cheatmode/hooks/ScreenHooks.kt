package io.github.krxwallo.cheatmode.hooks

import io.github.krxwallo.cheatmode.*
import io.github.krxwallo.cheatmode.extensions.addDrawableChild
import io.github.krxwallo.cheatmode.mixins.HandledScreenAccessor
import io.github.krxwallo.cheatmode.widget.ItemButton
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text
import net.minecraft.world.GameMode
import net.silkmc.silk.core.entity.executeCommand

object ScreenHooks {
    var button: ButtonWidget? = null

    fun onScreenInit(screen: Screen) {
        if (screen is InventoryScreen) {
            if (ConfigManager.client.instantCreativeInventory) creative()
            else {
                val handledScreen = screen as HandledScreenAccessor
                button = ItemButton(handledScreen.x + 77, handledScreen.y + 30) {
                    creative()
                }
                screen.addDrawableChild(button!!)
            }
        }
    }

    fun onScreenClose(screen: Screen) {
        if (screen is CreativeInventoryScreen) {
            if (!Manager.screenOpen) return
            Manager.screenOpen = false

            if (isSingleplayer) serverPlayer?.changeGameMode(Manager.previousGameMode)
            else {
                // Server -> send command
                mc.player?.executeCommand("gamemode ${Manager.previousGameMode.name.lowercase()}")
            }
        }
    }

    // try to set gamemode to creative
    private fun creative() {
        Manager.previousGameMode = mc.interactionManager?.currentGameMode ?: return

        if (isSingleplayer) serverPlayer?.changeGameMode(GameMode.CREATIVE)
        else mc.player?.executeCommand("gamemode creative")
    }

    fun onScreenDraw(screen: Screen, drawContext: DrawContext, mouseX: Int, mouseY: Int) {
        if (screen is InventoryScreen && button?.isMouseOver(mouseX.toDouble(), mouseY.toDouble()) == true)
            drawContext.drawTooltip(mc.textRenderer, Text.translatable("gui.cheatmode.open_creative_inventory"), mouseX, mouseY)
    }
}