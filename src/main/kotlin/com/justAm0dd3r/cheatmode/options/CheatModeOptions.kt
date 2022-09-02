package com.justAm0dd3r.cheatmode.options

import com.justAm0dd3r.cheatmode.ConfigManager
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.option.GameOptions
import net.minecraft.client.option.SimpleOption
import net.minecraft.text.Text
import net.minecraft.text.Text.translatable
import net.minecraft.util.math.MathHelper
import java.util.*

@Environment(EnvType.CLIENT)
object CheatModeOptions {
    val instantCreativeInventory: SimpleOption<Boolean> = SimpleOption.ofBoolean(
        "cheatmode.options.instant_creative_inventory",
        ConfigManager.client.instantCreativeInventory
    ) { value ->
        ConfigManager.client.instantCreativeInventory = value
        ConfigManager.save()
    }

    val flight: SimpleOption<Boolean> = SimpleOption.ofBoolean(
        "cheatmode.options.flight",
        ConfigManager.client.flight
    ) { value ->
        ConfigManager.client.flight = value
        ConfigManager.save()
    }

    @Suppress("INACCESSIBLE_TYPE")
    val reach: SimpleOption<Double> = SimpleOption(
        "cheatmode.options.reach",
        SimpleOption.constantTooltip(translatable("options.cheatmode.reach.tooltip")),
        { title, value -> GameOptions.getGenericValueText(title, Text.literal(String.format(Locale.ROOT, "%.1f", value * 50))) },
        SimpleOption.DoubleSliderCallbacks.INSTANCE.withModifier(MathHelper::square, Math::sqrt),
        ConfigManager.client.reach / 50,
        { value ->
            ConfigManager.client.reach = value * 50
            ConfigManager.save()
        }
    )
}