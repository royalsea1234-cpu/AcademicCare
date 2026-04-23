// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // This tells Gradle where to find the plugin and which version to use
    id("com.google.gms.google-services") version "4.4.4" apply false
}