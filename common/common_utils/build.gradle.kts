plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.common_utils"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":googlemap:map_domain"))

    implementation(Dependencies.core)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.constraintLayout)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
    testImplementation(TestImplementation.truth)
    androidTestImplementation(AndroidTestImplementation.truth)
    testImplementation(TestImplementation.coroutine)


    implementation(Retrofit.okHttp)
    implementation (Retrofit.gsonConvertor)



}