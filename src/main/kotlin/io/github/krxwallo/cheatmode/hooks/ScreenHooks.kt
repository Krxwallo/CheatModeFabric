package io.github.krxwallo.cheatmode.hooks

import io.github.krxwallo.cheatmode.*
import io.github.krxwallo.cheatmode.extensions.addDrawableChild
import io.github.krxwallo.cheatmode.mixins.HandledScreenAccessor
import io.github.krxwallo.cheatmode.widget.ItemButton
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.world.level.GameType

object ScreenHooks {
    var button: AbstractWidget? = null

    fun onScreenInit(screen: Screen) {
        if (screen is InventoryScreen) {
            if (ConfigManager.client.instantCreativeInventory) creative()
            else {
                val handledScreen = screen as HandledScreenAccessor
                button = ItemButton(handledScreen.leftPos + 77, handledScreen.topPos + 30) {
                    creative()
                }
                screen.addDrawableChild(button!!)
            }
        }
    }

    fun onScreenClose(screen: Screen) {
        if (screen is CreativeModeInventoryScreen) {
            if (!Manager.screenOpen) return
            Manager.screenOpen = false

            if (isSingleplayer) serverPlayer?.setGameMode(Manager.previousGameMode)
            else {
                mc.player?.connection?.sendCommand("gamemode ${Manager.previousGameMode.name.lowercase()}")
            }
        }
    }

    private fun creative() {
        Manager.previousGameMode = mc.gameMode?.playerMode ?: return
        if (isSingleplayer) serverPlayer?.setGameMode(GameType.CREATIVE)
        else mc.player?.connection?.sendCommand("gamemode creative")
    }
}
