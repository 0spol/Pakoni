plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.pakoni.network"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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

    // AndroidX Core y Compatibilidad
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI y Material
    implementation(libs.material)
    implementation(libs.androidx.material3)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Inyección de dependencias - Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    // Retrofit & Gson
    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)

    // Paging
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.common.android)

    // Coil (Carga de imágenes)
    implementation(libs.io.coil)

    // Pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}