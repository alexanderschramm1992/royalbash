import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.schramm"
version = 0.3
description = "the royalest of bashes"

plugins {
    id("java")
    kotlin("jvm") version "1.3.11"
    id("org.springframework.boot") version "2.1.2.RELEASE"
}

repositories {
    mavenCentral()
}

val springVersion by extra("5.1.3.RELEASE")

dependencies {
    // Swagger
    implementation("io.springfox:springfox-swagger2:2.6.1")
    implementation("io.springfox:springfox-swagger-ui:2.6.1")
    // Spring Boot Starter
    implementation("org.springframework.boot:spring-boot-starter:2.1.2.RELEASE") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.1.2.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:1.3.8.RELEASE")
    // MongoDB
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.1.2.RELEASE")
    implementation("org.mongodb:mongo-java-driver:3.9.1")
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.11")
    // Spring Boot
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.1.2.RELEASE")
    // Spring Boot Starter Test
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.1.2.RELEASE") {
        exclude(group = "junit", module = "junit")
    }
    // Architecture Tests
    testImplementation("com.tngtech.archunit:archunit:0.9.1")
    // AssertJ
    testImplementation("org.assertj:assertj-core:3.11.1")
    // Kotlin Test
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    // Mockk
    testImplementation("io.mockk:mockk:1.8.9")
    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
