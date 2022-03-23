import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version Versions.BEN_MANES
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
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