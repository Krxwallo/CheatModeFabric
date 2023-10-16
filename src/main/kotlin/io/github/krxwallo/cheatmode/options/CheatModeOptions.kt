package io.github.krxwallo.cheatmode.options

import io.github.krxwallo.cheatmode.ConfigManager
import io.github.krxwallo.cheatmode.mc
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.math.MathHelper
import kotlin.math.sqrt

@Environment(EnvType.CLIENT)
val options = arrayOf(
    BooleanOption("cheatmode.options.instant_creative_inventory", ConfigManager.client.instantCreativeInventory).onChange {
        ConfigManager.client.instantCreativeInventory = it
        ConfigManager.save()
    },
    BooleanOption("cheatmode.options.flight", ConfigManager.client.flight).onChange {
        ConfigManager.client.flight = it
        ConfigManager.save()
        if (!it) mc.player?.abilities?.flying = false
    },
    DoubleOption("cheatmode.options.reach", ConfigManager.client.reach, { MathHelper.square(it) * 50 }, { sqrt(it / 50) }).onChange {
        ConfigManager.client.reach = it
        ConfigManager.save()
    }
)