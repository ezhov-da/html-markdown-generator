plugins {
    application
    kotlin("jvm") version "1.9.23"
}

group = "ru.ezhov"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.commonmark:commonmark:0.22.0")
    implementation("org.commonmark:commonmark-ext-image-attributes:0.22.0")
    implementation("org.commonmark:commonmark-ext-gfm-tables:0.22.0")
    implementation("org.commonmark:commonmark-ext-task-list-items:0.22.0")
    implementation("org.commonmark:commonmark-ext-autolink:0.22.0")
    implementation("org.commonmark:commonmark-ext-gfm-strikethrough:0.22.0")
    implementation("org.commonmark:commonmark-ext-yaml-front-matter:0.22.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("ru.ezhov.htmlmarkdowngenerator.MainKt")
}