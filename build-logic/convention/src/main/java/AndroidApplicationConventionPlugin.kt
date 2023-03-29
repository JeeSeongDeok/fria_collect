package com.fria.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.fria.convention.convention.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig
import com.fria.convention.convention.configureKotlinAndroid
import com.fria.convention.convention.configurePrintApksTask
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
                configureGradleManagedDevices(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
        }
    }

}