plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.wdfeer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.qtjambi:qtjambi:6.7.2")
}

kotlin {
    jvmToolchain(21)
}