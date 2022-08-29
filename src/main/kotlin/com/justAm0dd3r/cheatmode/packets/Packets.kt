package com.justAm0dd3r.cheatmode.packets

import net.minecraft.util.Identifier

private fun id(name: String) = Identifier("cheatmode", name)

object Packets {
    val OPEN_INVENTORY = id("open_inventory")
    val OPEN_CREATIVE_INVENTORY = id("open_creative_inventory")
    val CLOSE_INVENTORY = id("close_inventory")
}