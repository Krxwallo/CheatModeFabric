package io.github.krxwallo.cheatmode

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.GameType

val mc: Minecraft get() = Minecraft.getInstance()
val isSingleplayer: Boolean get() = mc.isLocalServer
val serverPlayer: ServerPlayer?
    get() = mc.player?.uuid?.let { mc.singleplayerServer?.playerList?.getPlayer(it) }

object Manager: ClientModInitializer {
    var screenOpen = false
    var pendingCreativeScreen = false
    var temporaryCreative = false
    var previousGameMode = GameType.SURVIVAL

    override fun onInitializeClient() {
        ConfigManager.init()
    }
}
