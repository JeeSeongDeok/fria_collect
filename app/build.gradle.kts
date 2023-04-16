plugins {
    id("fria.android.application")
    id("fria.android.application.compose")
    id("fria.android.hilt")
}
android {
    namespace = "com.fria.collect"

    defaultConfig {
        applicationId = "com.fria.collect"
        versionCode = 1
        versionName = "0.0.1"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":data"))
    implementation(libs.ktx.core)
    implementation(libs.ktx.lifecycle)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.lifecycle)
    implementation(libs.accompanist.pager)
    implementation(libs.navigation)
    implementation(libs.hilt.navigation)
    implementation(libs.compose.coil)
    debugImplementation(libs.compose.tool)
}