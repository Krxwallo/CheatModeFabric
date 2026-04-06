package io.github.krxwallo.cheatmode.hooks

import io.github.krxwallo.cheatmode.Manager
import io.github.krxwallo.cheatmode.mc
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.world.flag.FeatureFlagSet
import net.minecraft.world.level.GameType

object InteractionManagerHooks {
    fun setMode(mode: GameType) {
        if (mc.screen is InventoryScreen && mode.isCreative) {
            Manager.screenOpen = true
            mc.setScreenAndShow(CreativeModeInventoryScreen(mc.player ?: return, FeatureFlagSet.of(), true))
        }
    }

    fun hasBars() = mc.gameMode?.playerMode?.isSurvival == true || Manager.screenOpen
}
