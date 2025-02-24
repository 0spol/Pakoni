plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.pakoni.home"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:data"))

    // --- Core Android dependencies ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // --- Dependency Injection (Hilt) ---
    implementation(libs.hilt)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.paging.compose.android)
    implementation(libs.androidx.paging)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.compose.runtime)


    // --- Room (Base de Datos) ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // --- Architecture Components (Lifecycle, ViewModel, Navigation) ---
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    // --- Jetpack Compose ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // --- Serialización JSON ---
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.io.coil)

    // --- Testing ---
    testImplementation(libs.junit)

    // Dependencias para pruebas en Android
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Dependencias para depuración en modo debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}