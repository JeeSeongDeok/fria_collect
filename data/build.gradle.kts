plugins {
    id("fria.android.library")
    id("fria.android.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

android {
    namespace = "com.fria.collect.data"
}

dependencies {
    implementation(project(":data:network"))
    implementation(project(":domain"))
    implementation(project(":model"))
}
