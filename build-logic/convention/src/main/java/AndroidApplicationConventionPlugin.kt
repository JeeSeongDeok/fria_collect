import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.fria.convention.convention.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.fria.convention.convention.configurePrintApksTask
import convention.configureAndroid
import convention.configureKotlin
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            configureAndroid()

            extensions.configure<BaseAppModuleExtension> {
                configureKotlin(this)

                buildTypes {
                    release {
                        postprocessing {
                            isRemoveUnusedCode = true
                            isRemoveUnusedResources = true
                            isOptimizeCode = true
                            isObfuscate = true
                            proguardFile("proguard-rules.pro")
                        }
                    }
                }

                packagingOptions {
                    resources {
                        excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                    }
                }

                lint {
                    checkOnly.add("Interoperability")
                    disable.add("ContentDescription")
                    abortOnError = false
                }
            }
        }
    }
}