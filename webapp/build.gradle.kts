import com.moowork.gradle.node.npm.NpmTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.moowork.node")
}

node {
    download = true
}

tasks.register<NpmTask>("npmStart") {
    dependsOn(rootProject.project("backend").tasks["build"], tasks["npmInstall"])

    setArgs(listOf("start"))
    setIgnoreExitValue(true)

    doFirst {
        val backendProject = rootProject.project("backend")
        val backendDir = backendProject.projectDir
        val backendArtifactName = backendProject.tasks.getByName<BootJar>("bootJar").archiveFileName.get()
        ext["backend"] = ProcessBuilder()
                .directory(backendDir)
                .command("java", "-jar", "build/libs/$backendArtifactName")
                .start()
    }

    doLast {
        ext["backend"]?.let {
            (it as Process).destroy()
        }
    }
}
