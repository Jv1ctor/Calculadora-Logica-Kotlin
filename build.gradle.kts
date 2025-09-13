kotlin {
    jvmToolchain(21)
}

plugins {
    kotlin("jvm") version "2.2.20"
    id("application")
    kotlin("plugin.serialization") version "2.2.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    // exemplo de lib extra:
    // implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt") // substitua pelo nome da sua classe principal
}
