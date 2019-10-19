import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    // cannot inline bytecode built with jvm target 1.8 into bytecode
    // that is being built with jvm target 1.6
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":navigation"))

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

    LifeCycle.run {
        implementation(EXT)
        implementation(VM_KTX)
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
