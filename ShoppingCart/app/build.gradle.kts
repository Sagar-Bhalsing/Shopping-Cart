plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)
}

android {
    compileSdk = 35
    namespace = "com.example.shoppingcart"
    defaultConfig {
        applicationId = "com.example.shoppingcart"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.20"
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.metadata.jvm)
    implementation ("io.coil-kt:coil-compose:2.4.0")

    implementation(libs.room.runtime)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose.android)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation ("com.squareup.moshi:moshi-kotlin:1.15.0")

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation(libs.hilt.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")

    implementation(libs.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.core.testing)
}