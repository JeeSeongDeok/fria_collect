package convention;
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */

import com.android.build.gradle.BaseExtension

import org.gradle.api.Action


import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureAndroid() {
    extensions.configure<BaseExtension> {
        compileSdkVersion(AppConfig.compileSdk)

        defaultConfig {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk

            testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
            resourceConfigurations.addAll(listOf("en", "ko"))
        }

        compileOptions {
            sourceCompatibility = AppConfig.JAVA_VERSION
            targetCompatibility = AppConfig.JAVA_VERSION
        }
    }
}

internal fun Project.configureKotlin(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xskip-prerelease-check",
            "-Xjvm-default=all",
            // Enable experimental coroutines APIs, including Flow
//            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
//            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        )

        // Set JVM target
        jvmTarget = AppConfig.JAVA_VERSION.toString()
        allWarningsAsErrors = true
    }
}

internal fun Project.`kapt`(
    configure: Action<KaptExtension>
) {
    (this as ExtensionAware).extensions.configure("kapt", configure)
}

internal fun Project.`java`(
    configure: Action<JavaPluginExtension>
) {
    (this as ExtensionAware).extensions.configure("java", configure)
}

internal fun Project.`kotlin`(
    configure: Action<KotlinJvmProjectExtension>
) {
    (this as ExtensionAware).extensions.configure("kotlin", configure)
}

internal fun CommonExtension<*, *, *, *>.kotlinOptions(
    configure: KotlinJvmOptions.() -> Unit
) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)
}