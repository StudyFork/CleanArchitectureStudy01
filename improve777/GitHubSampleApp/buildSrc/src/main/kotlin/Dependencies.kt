object ApplicationId {
    val id = "dev.daeyeon.githubsampleapp"
}

object Modules {
    val navigation = ":navigation"

    val app = ":app"

    val data = ":data"

    val domain = ":domain"

    val common = ":common"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.5.0"

    val compileSdk = 29
    val minSdk = 21
    val targetSdk = 29
    val buildTools = "29.0.1"

    val appcompat = "1.1.0"

    val ktx = "1.0.0"

    val constraintLayout = "1.1.3"

    val kotlin = "1.3.41"
    val timber = "4.7.1"
    val rxjava = "2.2.10"
    val rxkotlin = "2.3.0"
    val retrofit = "2.6.0"
    val loggingInterceptor = "4.0.0"
    val glide = "4.9.0"
    val koin = "2.0.1"

    val junit = "4.12"
    val androidJunit = "1.1.1"
    val espresso = "3.2.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val junit = "junit:junit:${Versions.junit}"
    val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object BuildPlugins {
    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    val androidApplication = "com.android.application"
    val kotlinAndroid = "kotlin-android"
    val kotlinAndroidExtensions = "kotlin-android-extensions"
    val kotlinKapt = "kotlin-kapt"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
}