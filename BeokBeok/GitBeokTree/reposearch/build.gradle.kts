plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    App.run {
        compileSdkVersion(COMPILE_SDK)
        buildToolsVersion(BUILD_TOOLS)

        defaultConfig {
            minSdkVersion(MIN_SDK)
            targetSdkVersion(TARGET_SDK)
            testInstrumentationRunner = Test.RUNNER
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":common"))

    App.run {
        implementation(fileTree(LIB_PATH))
        implementation(KOTLIN_STDLIB_JDK)
    }

    Test.run {
        implementation(JUNIT)
        implementation(ESPRESSO)
        implementation(KOTLIN_TEST_JUNIT)
    }

    Ui.run {
        implementation(MATERIAL)
        implementation(CONSTRAINT_LAYOUT)
    }

    Retrofit.run {
        implementation(RETROFIT)
        implementation(CONVERTER_GSON)
        implementation(LOGGING_INTERCEPTOR)
    }
}
