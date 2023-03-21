import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    // KTX
    const val CORE = "1.7.0"
    const val LIFECYCLE = "2.3.1"

    // COMPOSE
    const val COMPOSE = "1.3.1"
    const val MATERIAL = "1.0.0"

    // TEST
    const val JUNIT = "1.1.3"

    // Horizontal Pager
    const val PAGER = "0.23.1"
}

object Libraries {
    // KTX
    private const val coreKtx = "androidx.core:core-ktx:${Versions.CORE}"
    private const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"

    val ktxLibraries = arrayListOf<String>().apply {
        add(lifecycleKtx)
        add(coreKtx)
    }

    // Compose
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.COMPOSE}"
    private const val uiCompose = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    private const val previewCompose = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    private const val materialCompose = "androidx.compose.material3:material3:${Versions.MATERIAL}"
    private const val pagerCompose = "com.google.accompanist:accompanist-pager:${Versions.PAGER}"
    val composeLibraries = arrayListOf<String>().apply {
        add(activityCompose)
        add(uiCompose)
        add(previewCompose)
        add(materialCompose)
        add(pagerCompose)
    }


}
//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
//https://velog.io/@yuuuzzzin/Android-buildSrc-Kotlin-DSL%EB%A1%9C-Dependency-%EA%B4%80%EB%A6%AC%ED%95%98%EA%B8%B0