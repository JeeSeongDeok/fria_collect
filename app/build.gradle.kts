plugins {
    id("fria.android.application")
    id("fria.android.application.compose")
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
    implementation(libs.ktx.core)
    implementation(libs.ktx.lifecycle)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.compose.material)
    implementation(libs.accompanist.pager)
}