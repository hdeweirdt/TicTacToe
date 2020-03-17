plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.library)
}

dependencies {
    implementation(CommonDependencies.kotlinStdLib)
    implementation(InjectionDependencies.koin)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(InjectionDependencies.koinTest)
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
