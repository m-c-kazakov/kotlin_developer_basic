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
    testImplementation(kotlin("test-junit"))
}