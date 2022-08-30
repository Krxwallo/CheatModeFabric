package com.justAm0dd3r.cheatmode

import com.justAm0dd3r.cheatmode.packets.Packets
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameMode

val mc: MinecraftClient get() = MinecraftClient.getInstance()
val singleplayer get() = MinecraftClient.getInstance().isInSingleplayer
val serverPlayer get() = MinecraftClient.getInstance().server?.playerManager?.getPlayer(MinecraftClient.getInstance().player?.uuid)
private var firstTime = true

object Hooks {
    @Environment(EnvType.CLIENT)
    fun initClient() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.OPEN_CREATIVE_INVENTORY) { mc, _, _, _ ->
            openCreativeInventory(mc)
        }
    }

    @Environment(EnvType.SERVER)
    fun initServer() {
        ServerPlayNetworking.registerGlobalReceiver(Packets.OPEN_INVENTORY) { _, player, _, _, _ ->
            onOpenInventory(player)
        }
        ServerPlayNetworking.registerGlobalReceiver(Packets.CLOSE_INVENTORY) { _, player, _, _, _ ->
            onCloseInventory(player)
        }
    }

    fun onScreenInit(screen: Screen) {
        if (screen !is InventoryScreen) return

        if (!firstTime) return
        firstTime = false

        if (singleplayer) onOpenInventory(serverPlayer ?: return) else ClientPlayNetworking.send(Packets.OPEN_INVENTORY, PacketByteBufs.empty())
    }

    fun onScreenClose(screen: Screen) {
        if (screen !is CreativeInventoryScreen) return
        firstTime = true
        if (singleplayer) onCloseInventory(serverPlayer ?: return) else ClientPlayNetworking.send(Packets.CLOSE_INVENTORY, PacketByteBufs.empty())
    }

    private fun onOpenInventory(player: ServerPlayerEntity) {
        player.changeGameMode(GameMode.CREATIVE)
        if (player.server.isSingleplayer) openCreativeInventory(mc) else ServerPlayNetworking.send(player, Packets.OPEN_CREATIVE_INVENTORY, PacketByteBufs.empty())
    }

    private fun onCloseInventory(player: ServerPlayerEntity) {
        player.changeGameMode(player.interactionManager.previousGameMode)
    }

    private fun openCreativeInventory(mc: MinecraftClient) {
        mc.setScreen(CreativeInventoryScreen(mc.player))
    }
}