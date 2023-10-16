val javaVersion = 17
val silkVersion = "1.10.2"

plugins {
    kotlin("jvm") version "1.9.10"
    id("fabric-loom") version "1.3-SNAPSHOT"
    kotlin("plugin.serialization") version "1.9.10"
}

group = "io.github.krxwallo"
version = "1.20.X-1.1.0-alpha"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.2")
    mappings("net.fabricmc:yarn:1.20.2+build.4")
    modImplementation("net.fabricmc:fabric-loader:0.14.23")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.90.0+1.20.2")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.10.10+kotlin.1.9.10")

    modImplementation("net.silkmc:silk-core:$silkVersion")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjdk-release=$javaVersion", "-Xskip-prerelease-check")
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}
