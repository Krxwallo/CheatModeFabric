package io.github.krxwallo.cheatmode.hooks

import io.github.krxwallo.cheatmode.ConfigManager
import io.github.krxwallo.cheatmode.Manager
import io.github.krxwallo.cheatmode.mc
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.world.GameMode

object InteractionManagerHooks {
    fun setMode(mode: GameMode) {
        if (mc.currentScreen is InventoryScreen && mode.isCreative) {
            Manager.screenOpen = true
            mc.setScreenAndRender(CreativeInventoryScreen(mc.player ?: return, FeatureSet.empty(), true))
        }
    }

    fun hasBars() = mc.interactionManager!!.currentGameMode.isSurvivalLike || Manager.screenOpen

    fun getReach() = ConfigManager.client.reach.toFloat()
}