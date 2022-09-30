import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.coreKtx() {
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
}

fun DependencyHandler.lifecycle() {
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}")
}

fun DependencyHandler.activityCompose() {
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
}

fun DependencyHandler.compose() {
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
}

fun DependencyHandler.composeDebug() {
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose}")
}

fun DependencyHandler.material3() {
    implementation("androidx.compose.material3:material3:${Versions.material3}")
}

fun DependencyHandler.junit() {
    testImplementation("junit:junit:${Versions.junit}")
}

fun DependencyHandler.androidTest() {
    androidTestImplementation("androidx.test.ext:junit:${Versions.junitExtensions}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoCore}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
}

///////////////
// HELPERS:
///////////////

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}
