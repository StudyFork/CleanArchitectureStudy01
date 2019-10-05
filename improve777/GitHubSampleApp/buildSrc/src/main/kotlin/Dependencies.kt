object ApplicationId {
    const val id = "dev.daeyeon.githubsampleapp"
}

object Modules {
    const val navigation = ":navigation"

    const val app = ":app"

    const val data = ":data"

    const val domain = ":domain"

    const val common = ":common"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val gradle = "3.5.0"

    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val buildTools = "29.0.1"

    const val kotlin = "1.3.41"
    const val appcompat = "1.1.0"
    const val lifecycleExt = "2.1.0"

    const val room = "2.2.0-rc01"

    const val material = "1.0.0"

    const val ktx = "1.1.0"

    const val anko = "0.10.8"

    const val constraintLayout = "1.1.3"

    const val timber = "4.7.1"

    const val rxJava = "2.2.12"
    const val rxKotlin = "2.4.0"
    const val rxAndroid = "2.1.1"

    const val retrofit = "2.6.0"
    const val loggingInterceptor = "4.2.0"

    const val tedPermission = "2.2.2"

    const val glide = "4.9.0"

    const val koin = "2.0.1"

    const val junit = "4.12"
    const val androidJunit = "1.1.1"
    const val espresso = "3.2.0"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val room = "androidx.room:room-runtime:$${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
    const val roomTest = "androidx.room:room-testing:${Versions.room}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val tedPermission = "gun0912.ted:tedpermission:${Versions.tedPermission}"

    const val anko = "org.jetbrains.anko:anko-commons:${Versions.anko}"

    const val koinCore = "org.koin:koin-core:$${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val androidApplication = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val javaLibrary = "java-library"
    const val androidLibrary = "com.android.library"
}