package io.github.krxwallo.cheatmode

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameMode
import net.silkmc.silk.commands.*
import net.silkmc.silk.core.text.literal

val mc: MinecraftClient get() = MinecraftClient.getInstance()
val isSingleplayer: Boolean get() = mc.isInSingleplayer
val serverPlayer: ServerPlayerEntity? get() = mc.server?.playerManager?.getPlayer(mc.player?.uuid)

object Manager: ClientModInitializer {
    var screenOpen = false
    var previousGameMode = GameMode.SURVIVAL

    override fun onInitializeClient() {
        ConfigManager.init()

        clientCommand("survivalfix") {
            runs {
                if (isSingleplayer) {
                    serverPlayer?.changeGameMode(GameMode.SURVIVAL)
                    source.sendSuccess("Set gamemode to survival.".literal)
                }
                else {
                    source.sendFailure("Doesn't work in multiplayer.".literal)
                }
            }
        }
    }
}