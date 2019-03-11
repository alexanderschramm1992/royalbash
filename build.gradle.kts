import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.21" apply false
    id("org.springframework.boot") version "2.1.3.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.7.RELEASE" apply false
    id("com.moowork.node") version "1.2.0" apply false
}

allprojects {
    group = "de.schramm"
    version = 0.3
    description = "the royalest of bashes"

    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

tasks.register("build") {
    dependsOn(
            project("backend").tasks["build"],
            project("webapp").tasks["npmInstall"]
    )
}
