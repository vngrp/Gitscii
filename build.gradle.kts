plugins {
    kotlin("jvm") version "1.9.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.4"
}

group = "com.vngrp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.4")
    implementation("com.lordcodes.turtle:turtle:0.8.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}