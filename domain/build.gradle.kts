plugins {
    id("fria.android.application")
    id("fria.android.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

android {
    namespace = "com.fria.collect.domain"
}

dependencies {
    implementation(project(":model"))
    implementation(project(":common"))
    implementation(libs.retrofit.core)
}
