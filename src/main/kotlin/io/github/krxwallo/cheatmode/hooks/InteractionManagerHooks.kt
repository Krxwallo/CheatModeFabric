package io.github.krxwallo.cheatmode.hooks

import io.github.krxwallo.cheatmode.Manager
import io.github.krxwallo.cheatmode.mc
import net.minecraft.world.level.GameType

object InteractionManagerHooks {
    fun setMode(mode: GameType) {
        ScreenHooks.onLocalGameModeChange(mode)
    }

    fun hasBars() = mc.gameMode?.playerMode?.isSurvival == true || Manager.screenOpen
}
