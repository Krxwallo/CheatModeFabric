import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val javaVersion = JvmTarget.JVM_25
val minecraftVersion = "26.2"
val fabricLoaderVersion = "0.19.3"
val fabricApiVersion = "0.156.0+26.2"
val fabricLanguageKotlinVersion = "1.13.10+kotlin.2.3.20"

plugins {
    kotlin("jvm") version "2.3.20"
    id("net.fabricmc.fabric-loom") version "1.16.1"
    kotlin("plugin.serialization") version "2.3.20"
}

group = "io.github.krxwallo"
version = "26.2-1.3.0-beta"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion.target.toInt())
    }
}

kotlin {
    jvmToolchain(javaVersion.target.toInt())
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    implementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    implementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
    implementation("net.fabricmc:fabric-language-kotlin:$fabricLanguageKotlinVersion")
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjdk-release=${javaVersion.target}", "-Xskip-prerelease-check")
            jvmTarget.set(javaVersion)
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion.target.toInt())
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}
