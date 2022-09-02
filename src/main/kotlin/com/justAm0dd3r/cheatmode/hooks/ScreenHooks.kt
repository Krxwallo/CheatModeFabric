package com.justAm0dd3r.cheatmode.hooks

import com.justAm0dd3r.cheatmode.*
import com.justAm0dd3r.cheatmode.mixins.HandledScreenAccessor
import com.justAm0dd3r.cheatmode.utils.addButton
import com.justAm0dd3r.cheatmode.widget.ItemButton
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.world.GameMode

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
                screen.addButton(button!!)
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
                mc.player?.sendCommand("gamemode ${Manager.previousGameMode.name.lowercase()}")
            }
        }
    }

    // try to set gamemode to creative
    private fun creative() {
        Manager.previousGameMode = mc.interactionManager?.currentGameMode ?: return

        if (isSingleplayer) serverPlayer?.changeGameMode(GameMode.CREATIVE)
        else mc.player?.sendCommand("gamemode creative")
    }

    // maybe render tooltip
    fun onScreenDraw(screen: Screen, stack: MatrixStack, mouseX: Int, mouseY: Int) {
        if (screen is InventoryScreen && button?.isMouseOver(mouseX.toDouble(), mouseY.toDouble()) == true)
            screen.renderTooltip(stack, Text.translatable("gui.cheatmode.open_creative_inventory"), mouseX, mouseY)
    }
}