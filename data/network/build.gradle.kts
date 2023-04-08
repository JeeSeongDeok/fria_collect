plugins {
    id("fria.android.application")
    id("fria.android.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

android {
    namespace = "com.fria.collect.data.network"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(":model")
    implementation(project(mapOf("path" to ":model")))
}
