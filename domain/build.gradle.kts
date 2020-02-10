plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.library)
}

dependencies {
    implementation(CommonDependencies.kotlinStdLib)
    implementation(TestDependencies.junit)
    implementation(TestDependencies.mockk)
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
