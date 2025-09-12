kotlin {
    jvmToolchain(21)
}

plugins {
    kotlin("jvm") version "1.9.22" // use a versão mais recente do Kotlin instalada
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // exemplo de lib extra:
    // implementation("com.google.code.gson:gson:2.10.1")
}

application {
    mainClass.set("MainKt") // substitua pelo nome da sua classe principal
}
