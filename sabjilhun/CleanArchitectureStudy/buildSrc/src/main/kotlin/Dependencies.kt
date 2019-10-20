const val kotlinVersion = "1.3.41"

object BuildPlugins {
    object Versions {
        const val androidBuildToolsVersion = "3.5.0"
    }

    const val androidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.androidBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
}

object AndroidSdk {
    const val min = 23
    const val compile = 28
    const val target = 28
}

object Version {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {
    const val app = ":app"
    const val common = ":common"
    const val domain = ":domain"
    const val navigator = ":navigator"
    const val data = ":data"

    // Features
    const val search = ":features:search"
    const val filetree = ":features:filetree"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0-alpha02"
        const val constraintLayout = "2.0.0-alpha3"
        const val material = "1.0.0"
        const val retrofit = "2.6.2"
        const val okhttp = "4.2.0"
        const val ktx = "1.1.0-alpha04"
        const val archLifecycle = "2.2.0-alpha04"
        const val rxJava = "2.2.9"
        const val rxAndroid = "2.1.1"
        const val rxKotlin = "2.4.0"
        const val dagger = "2.24"
        const val glide = "4.10.0"

        const val spinner = "1.1.0"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val materialComponents = "com.google.android.material:material:${Versions.material}"

    // For Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    // For KTX
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"

    // For AAC
    const val archLifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"

    // For Reactive
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"

    // For Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidAnnotation =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // For View
    const val spinner = "com.weiwangcn.betterspinner:library:${Versions.spinner}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13-beta-3"
        const val runner = "1.3.0-alpha02"
        const val espresso = "3.3.0-alpha02"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
