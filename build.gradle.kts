plugins {
    kotlin("jvm") version "2.0.0"
    application
}

application {
    mainClass.set("org.wdfeer.kards.qt.MainKt")
}

group = "org.wdfeer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.qtjambi:qtjambi:6.7.2")
    implementation("io.qtjambi:qtjambi-native-linux-x64:6.7.2")
}

kotlin {
    jvmToolchain(21)
}