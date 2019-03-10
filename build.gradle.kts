import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "de.schramm"
version = 0.3
description = "the royalest of bashes"

plugins {
    id("java")
    kotlin("jvm") version "1.3.21"
    id("org.springframework.boot") version "2.1.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.21"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

repositories {
    mavenCentral()
}

val swaggerVersion by extra("2.6.1")
val junitVersion by extra("5.3.2")

springBoot {
    mainClassName = "de.schramm.royalbash.ServerApplicationKt"
}

the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    // Swagger
    implementation("io.springfox:springfox-swagger2:$swaggerVersion")
    implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")
    // Spring Boot Starter
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // Spring Boot
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    // Spring Boot Starter Test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
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
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.getByName<BootJar>("bootJar") {
    archiveClassifier.set("boot")
    isExcludeDevtools = false
}
