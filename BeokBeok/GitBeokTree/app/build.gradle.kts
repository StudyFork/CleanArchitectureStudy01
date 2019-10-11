plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    App.run {
        compileSdkVersion(COMPILE_SDK)
        buildToolsVersion(BUILD_TOOLS)

        defaultConfig {
            applicationId = APP_ID
            minSdkVersion(MIN_SDK)
            targetSdkVersion(TARGET_SDK)
            testInstrumentationRunner = Test.RUNNER
        }
    }

    Release.run {
        defaultConfig {
            versionCode = VERSION_CODE
            versionName = VERSION_NAME
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

    // Static interface methods are only supported starting with Android N (--min-api 24)
    // https://github.com/JakeWharton/butterknife/issues/1416#issuecomment-444164629
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":repoSearch"))
    implementation(project(":repoBrowse"))

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

    Koin.run {
        implementation(KOIN)
        implementation(VIEWMODEL)
    }

    Navigation.run {
        implementation(FRAGMENT)
        implementation(UI)
    }
}
