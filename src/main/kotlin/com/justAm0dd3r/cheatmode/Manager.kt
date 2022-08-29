package com.justAm0dd3r.cheatmode

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger("CheatMode")

object Manager: ModInitializer, DedicatedServerModInitializer, ClientModInitializer {

    override fun onInitialize() {}

    override fun onInitializeClient() {
        logger.info("client")
        Hooks.initClient()
    }

    override fun onInitializeServer() {
        logger.info("server")
        Hooks.initServer()
    }
}