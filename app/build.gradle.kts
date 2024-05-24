plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidxNavigation)
    kotlin("kapt")


}

android {
    namespace = "com.example.travelai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.travelai"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
        dataBinding = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.android.lifecycle.compose)
    implementation(libs.android.lifecycle.livedata)
    implementation(libs.android.lifecycle)
    implementation(libs.android.recyclerview.selection)
    implementation(libs.android.recylcerview)
    implementation(libs.com.github.bumptech.glide)
    implementation(libs.com.google.dagger.hilt)
    implementation(libs.com.google.dagger)
    implementation(libs.com.google.libraries.places)
//    implementation(libs.com.arasthel.spanned)
    testImplementation(libs.junit)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.com.google.play.services.maps)
    annotationProcessor(libs.com.github.bumptech.glide.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    kapt(libs.daggerCompiler)
    kapt(libs.lifecycle.livedata)

}