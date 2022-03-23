plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
    id(Plugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        applicationId = DefaultConfig.APPLICATION_ID
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK
        versionCode = DefaultConfig.VERSION_CODE
        versionName = DefaultConfig.VERSION_NAME

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTAL_RUNNER
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:history"))

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.TEST_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)

    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.GSON_CONVERTER)

    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)

    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)

    debugImplementation(Dependencies.LEAK_CANARY)
}