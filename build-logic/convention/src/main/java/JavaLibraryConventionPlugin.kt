@file:Suppress("unused")
package com.fria.convention


import com.fria.convention.convention.AppConfig
import com.fria.convention.convention.java
import com.fria.convention.convention.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class JavaLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
//        with(target) {
//            with(pluginManager) {
//                apply("java-library")
//                apply("org.jetbrains.kotlin.jvm")
//            }
//
//            java {
//                sourceCompatibility = AppConfig.JAVA_VERSION
//                targetCompatibility = AppConfig.JAVA_VERSION
//            }
//
//            kotlin {
//                jvmToolchain(AppConfig.JDK_VERSION)
//            }
//        }
    }
}