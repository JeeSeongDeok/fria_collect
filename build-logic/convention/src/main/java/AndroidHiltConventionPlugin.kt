import com.fria.convention.convention.findVersionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import convention.implementation
import convention.kapt
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
                apply("dagger.hilt.android.plugin")
            }

            val libs = findVersionCatalog()

            dependencies {
                implementation(libs.findLibrary("dagger-hilt-android"))
                kapt(libs.findLibrary("dagger-hilt-compiler"))
            }

            kapt {
                correctErrorTypes = true
            }
        }
    }
}