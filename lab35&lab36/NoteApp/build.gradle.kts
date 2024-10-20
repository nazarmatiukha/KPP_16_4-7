plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs.kotlin")  version "2.8.2" apply false

}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.8.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
