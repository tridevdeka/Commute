object Versions {
    const val core = "1.9.0"
    const val appcompat = "1.5.1"
    const val androidMaterial = "1.6.0"
    const val constraintLayout = "2.1.4"

    const val testImplJunit = "4.13.2"
    const val androidTestImplJunit = "1.1.3"
    const val androidTestEspresso = "3.4.0"

    const val navigation = "2.5.3"

    const val retrofit = "2.9.0"
    const val loggingInterceptor = "5.0.0-alpha.6"
    const val gsonConvertor = "2.9.0"
    const val okHttp = "4.9.0"
    const val scalarConvertor = "2.1.0"

    const val kotlinCoroutines = "1.6.1"

    const val lifecycleVersions = "2.5.1"

    const val glide = "4.13.2"

    const val viewModelDelegate = "1.6.0"

    const val dagger = "2.44.2"
    const val hiltCompiler = "1.0.0"

    const val roomVersion = "2.4.3"

    const val swipeRefresh = "1.1.0"

    const val lottieAnimations = "3.4.2"

    const val timber = "4.5.1"
    const val makeraman = "2.3.0"

    const val truth = "1.1.3"

}


object Dependencies {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Navigation {
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}


object TestImplementation {
    const val junit = "junit:junit:${Versions.testImplJunit}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val coroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:$${Versions.kotlinCoroutines}"

}

object AndroidTestImplementation {
    const val junit = "androidx.test.ext:junit:${Versions.androidTestImplJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
    const val truth = "com.google.truth:truth:${Versions.truth}"

}


object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Versions.gsonConvertor}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val scalersConvertors =
        "com.squareup.retrofit2:converter-scalars:${Versions.scalarConvertor}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

}

object Coroutines {
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
}

object CoroutinesLifecycleScope {
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersions}"
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersions}"
}

object LiveData {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersions}"

}

object ViewModel {
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersions}"
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersions}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val annotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object ViewModelDelegate {
    const val viewModelDelegate = "androidx.activity:activity-ktx:${Versions.viewModelDelegate}"
}

object DaggerHilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltCompiler}"
}

object Room {
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val room = "androidx.room:room-ktx:${Versions.roomVersion}"
}

object CircularProgressBar {
    const val swipeRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
}

object LottieAnimations {
    const val lottieAnimations = "com.airbnb.android:lottie:${Versions.lottieAnimations}"
}

object RoundedImageView {
    const val roundedImage = "com.makeramen:roundedimageview:${Versions.makeraman}"

}
