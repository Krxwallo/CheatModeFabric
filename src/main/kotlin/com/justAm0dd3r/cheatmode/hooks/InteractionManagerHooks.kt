package com.justAm0dd3r.cheatmode.hooks

import com.justAm0dd3r.cheatmode.ConfigManager
import com.justAm0dd3r.cheatmode.Manager
import com.justAm0dd3r.cheatmode.mc
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.world.GameMode

object InteractionManagerHooks {
    fun setMode(mode: GameMode) {
        if (mc.currentScreen is InventoryScreen && mode.isCreative) {
            Manager.screenOpen = true
            mc.setScreenAndRender(CreativeInventoryScreen(mc.player ?: return))
        }
    }

    fun hasBars() = mc.interactionManager!!.currentGameMode.isSurvivalLike || Manager.screenOpen

    fun getReach() = ConfigManager.client.reach.toFloat()
}