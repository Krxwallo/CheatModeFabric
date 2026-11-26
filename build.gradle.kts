import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val javaVersion = JvmTarget.JVM_25
val minecraftVersion = "26.3"
val fabricLoaderVersion = "0.20.1"
val fabricApiVersion = "0.166.2+26.3"
val fabricLanguageKotlinVersion = "1.14.2+kotlin.2.4.21"

plugins {
    kotlin("jvm") version "2.4.21"
    id("net.fabricmc.fabric-loom") version "1.19-SNAPSHOT"
    kotlin("plugin.serialization") version "2.4.21"
}

group = "io.github.krxwallo"
version = "26.3-1.3.0-beta"

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
