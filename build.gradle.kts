plugins {
    kotlin("jvm") version "2.0.0"
    application
}

application {
    mainClass.set("org.wdfeer.kards.qt.MainKt")
}

group = "org.wdfeer"
version = "0.1-alpha"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation("io.qtjambi:qtjambi:6.7.2")
    implementation("io.qtjambi:qtjambi-native-linux-x64:6.7.2")
}

kotlin {
    jvmToolchain(21)
}