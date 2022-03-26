import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(Plugins.BEN_MANES) version Versions.BEN_MANES
    id(Plugins.ORG_JETBRAINS_KOTLIN_ANDROID) version Versions.KOTLIN_GRADLE apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.GRADLE)
        classpath(BuildPlugins.KOTLIN_GRADLE)
        classpath(BuildPlugins.NAVIGATION_SAFE_ARGS)
        classpath(BuildPlugins.HILT_GRADLE)
    }
}

tasks {
    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            candidate.version.isNonStable()
        }
    }
    register(name = "type", type = Delete::class) {
        delete(rootProject.buildDir)
    }
}