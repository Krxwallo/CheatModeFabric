package com.justAm0dd3r.cheatmode

import com.mojang.logging.LogUtils
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class ClientConfig(
    var instantCreativeInventory: Boolean = true,
    var flight: Boolean = false,
    var reach: Double = 3.0,
)

object ConfigManager {
    @Suppress("OPT_IN_USAGE")
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
        prettyPrintIndent = "  "
        ignoreUnknownKeys = true
    }

    private val configFile = File("${mc.runDirectory}${File.separator}config${File.separator}cheatmode-client.json").apply {
        if (!exists()) {
            LogUtils.getLogger().warn("No config file found. Creating new one.")
            writeText("{}")
        }
    }

    var client = ClientConfig()

    fun init() {
        client = json.decodeFromString(configFile.readText())
    }

    fun save() {
        configFile.writeText(json.encodeToString(client))
    }
}