plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "com.example.testing"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.testing"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {

    implementation("org.chromium.net:cronet-embedded:113.5672.61")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.shopify.mobilebuysdk:buy3:16.1.0") {
        exclude (group = "com.shopify.graphql.support", module= "support")
    }
    implementation("com.shopify:sdk:9.0.2")
    implementation ("com.shopify.mobile:buy3:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.apollographql.apollo:apollo-runtime:2.5.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")


}
