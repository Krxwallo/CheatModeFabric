package io.github.krxwallo.cheatmode

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameMode

val mc: MinecraftClient get() = MinecraftClient.getInstance()
val isSingleplayer: Boolean get() = mc.isInSingleplayer
val serverPlayer: ServerPlayerEntity? get() = mc.server?.playerManager?.getPlayer(mc.player?.uuid)

object Manager: ClientModInitializer {
    var screenOpen = false
    var previousGameMode = GameMode.SURVIVAL

    override fun onInitializeClient() {
        ConfigManager.init()
    }
}