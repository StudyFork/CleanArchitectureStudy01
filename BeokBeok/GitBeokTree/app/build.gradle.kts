plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    AppDependencies.run {
        compileSdkVersion(COMPILE_SDK)
        buildToolsVersion(BUILD_TOOLS)

        defaultConfig {
            minSdkVersion(MIN_SDK)
            targetSdkVersion(TARGET_SDK)
            testInstrumentationRunner = TestDependencies.RUNNER
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
}

dependencies {
    AppDependencies.run {
        implementation(fileTree(LIB_PATH))
        implementation(KOTLIN_STDLIB_JDK)
    }

    TestDependencies.run {
        implementation(JUNIT)
        implementation(ESPRESSO)
        implementation(KOTLIN_TEST_JUNIT)
    }

    UiDependencies.run {
        implementation(MATERIAL)
        implementation(CONSTRAINT_LAYOUT)
    }
}