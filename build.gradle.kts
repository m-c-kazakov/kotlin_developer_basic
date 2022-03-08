
plugins {
    kotlin("jvm") version "1.6.20-M1"
}

group = "com.otus.kotlin"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-test:1.6.0")
}