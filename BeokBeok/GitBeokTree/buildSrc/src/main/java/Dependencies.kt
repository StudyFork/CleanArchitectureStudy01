object BuildScriptDependencies {
    private const val GRADLE_VERSION = "3.5.1"
    const val KOTLIN_VERSION = "1.3.50"

    const val GRADLE = "com.android.tools.build:gradle:$GRADLE_VERSION"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}

object AppDependencies {
    const val COMPILE_SDK = 29
    const val BUILD_TOOLS = "29.0.2"
    const val MIN_SDK = 23
    const val TARGET_SDK = 29
    const val APP_ID = "com.beok.gitbeoktree"

    const val KOTLIN_STDLIB_JDK =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildScriptDependencies.KOTLIN_VERSION}"
    val LIB_PATH = mapOf(
        "dir" to "libs",
        "include" to listOf("*.jar")
    )
}

object Release {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object TestDependencies {
    private const val JUNIT_VERSION = "1.1.1"
    private const val ESPRESSO_VERSION = "3.2.0"

    const val RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val JUNIT = "androidx.test.ext:junit:$JUNIT_VERSION"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val KOTLIN_TEST_JUNIT =
        "org.jetbrains.kotlin:kotlin-test-junit:${BuildScriptDependencies.KOTLIN_VERSION}"
}

object UiDependencies {
    private const val MATERIAL_VERSION = "1.0.0"
    private const val CONSTRAINT_VERSION = "1.1.3"

    const val MATERIAL = "com.google.android.material:material:$MATERIAL_VERSION"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_VERSION"
}

object LifeCycleDependencies {
    private const val VERSION = "2.1.0"

    const val EXT = "androidx.lifecycle:lifecycle-extensions:$VERSION"
    const val VM_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
}

object NavigationDependencies {
    private const val VERSION = "2.1.0"

    const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"
    const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
}