plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.ORG_JETBRAINS_KOTLIN_ANDROID)
    id(Plugins.NAVIGATION_SAFE_ARGS)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
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
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":navigation"))
    implementation(project(":domain"))

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.TEST_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)

    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
}