plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "be.harm.deweirdt.TicTacToe"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree("libs"))

    implementation(CommonDependencies.kotlinStdLib)
    implementation(AndroidDependencies.appCompat)
    implementation(AndroidDependencies.core)
    implementation(AndroidDependencies.lifecycleExtensions)
    implementation(AndroidDependencies.lifecycleLiveData)
    implementation(AndroidDependencies.lifecycleViewModel)

    implementation(UiDependencies.constraintLayout)
    implementation(UiDependencies.materialDesign)
    implementation(UiDependencies.navigation_fragment)
    implementation(UiDependencies.navigation_ui)


    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(CommonDependencies.coroutinesTest)
    testImplementation(TestDependencies.androidArchCoreTest)

    androidTestImplementation(TestDependencies.android_junit)
}
