// Top-level build file where you can add configuration options common to all sub-projects/modules.
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

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}