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
        if (screen is CreativeModeInventoryScreen && Manager.screenOpen) {
            restorePreviousGameMode()
        }
    }

    fun onLocalGameModeChange(mode: GameType) {
        if (!mode.isCreative || !Manager.temporaryCreative || !Manager.pendingCreativeScreen) return
        Manager.pendingCreativeScreen = false

        val player = mc.player
        if (player == null || mc.gui.screen() !is InventoryScreen) {
            restorePreviousGameMode()
            return
        }

        Manager.screenOpen = true
        mc.gui.setScreen(CreativeModeInventoryScreen(
            player,
            player.connection.enabledFeatures(),
            mc.options.operatorItemsTab().get()
        ))
    }

    fun restorePreviousGameMode() {
        if (!Manager.temporaryCreative) return

        if (isSingleplayer) {
            val player = serverPlayer ?: return
            player.setGameMode(Manager.previousGameMode)
        } else {
            val player = mc.player ?: return
            player.connection.sendCommand("gamemode ${Manager.previousGameMode.name.lowercase()}")
        }

        Manager.temporaryCreative = false
        Manager.pendingCreativeScreen = false
        Manager.screenOpen = false
    }

    private fun creative() {
        val currentMode = mc.gameMode?.playerMode ?: return
        if (currentMode.isCreative) return

        Manager.previousGameMode = currentMode
        Manager.pendingCreativeScreen = mc.gui.screen() is InventoryScreen
        Manager.temporaryCreative = true

        if (isSingleplayer) {
            val player = serverPlayer
            if (player == null) {
                Manager.temporaryCreative = false
                Manager.pendingCreativeScreen = false
                return
            }
            player.setGameMode(GameType.CREATIVE)
        }
        else mc.player?.connection?.sendCommand("gamemode creative")
    }
}
