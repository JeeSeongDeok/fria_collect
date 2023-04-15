plugins {
    id("fria.android.library")
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
    implementation(project(":model"))
    implementation(project(":common"))
}
