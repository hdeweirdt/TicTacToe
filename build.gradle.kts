buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    apply(plugin = BuildPlugins.ktLint)
}

plugins {
    id(BuildPlugins.ktLint) version BuildPlugins.Versions.ktlintVersion
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
