object BuildPlugins {

    const val GRADLE: String = "com.android.tools.build:gradle:${Versions.GRADLE}"

    const val KOTLIN_GRADLE: String =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE}"

    const val NAVIGATION_SAFE_ARGS: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"

    const val HILT_GRADLE: String = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

    const val KOTLIN_KSP_GRADLE: String =
        "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.5.31-1.0.1"

}