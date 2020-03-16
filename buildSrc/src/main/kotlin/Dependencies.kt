const val kotlinVersion = "1.3.61"

object BuildPlugins {

    object Versions {
        const val gradleVersion = "3.5.2"
        const val ktlintVersion = "9.1.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlin = "kotlin"
    const val library = "java-library"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

    const val ktLint = "org.jlleitschuh.gradle.ktlint"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object CommonDependencies {
    private object Versions {
        const val coroutinesVersion = "1.3.3"
        const val koinVersion = "2.1.4"
    }


    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val koin = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
}

object AndroidDependencies {
    private object Versions {
        const val appcompat = "1.1.0"
        const val core = "1.1.0"
        const val lifeCycleExtensions = "2.2.0-rc03"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtensions}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleExtensions}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleExtensions}"
}

object UiDependencies {
    private object Versions {
        const val constraintLayoutVersion = "1.1.3"
        const val materialDesignVersion = "1.1.0"
        const val navigationVersion = "2.1.0"
    }

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesignVersion}"
}

object TestDependencies {
    private object Versions {
        const val junit = "4.12"
        const val androidJunit = "1.1.1"
        const val mockk = "1.9.2"
        const val androidArchCoreTestVersion = "2.0.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val android_junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val androidArchCoreTest =
        "androidx.arch.core:core-testing:${Versions.androidArchCoreTestVersion}"
}