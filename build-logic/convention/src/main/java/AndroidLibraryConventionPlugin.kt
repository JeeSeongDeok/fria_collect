import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.fria.convention.convention.configureFlavors
import com.fria.convention.convention.configureGradleManagedDevices
import com.fria.convention.convention.configurePrintApksTask
import convention.configureAndroid
import convention.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            configureAndroid()

            extensions.configure<LibraryExtension> {
                configureKotlin(this)

                lint {
                    checkOnly.add("Interoperability")
                    disable.add("ContentDescription")
                    abortOnError = false
                }
            }
        }
    }
}