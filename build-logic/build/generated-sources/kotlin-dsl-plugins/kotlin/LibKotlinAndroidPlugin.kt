/**
 * Precompiled [lib-kotlin-android.gradle.kts][Lib_kotlin_android_gradle] script plugin.
 *
 * @see Lib_kotlin_android_gradle
 */
class LibKotlinAndroidPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Lib_kotlin_android_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
