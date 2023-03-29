plugins {
    `kotlin-dsl`
}

group = "com.fria.collect.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "fria.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("javaLibrary") {
            id = "fria.java.library"
            implementationClass = "JavaLibraryConventionPlugin"
        }
    }
}