import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.koin() {
    implementation("io.insert-koin:koin-core:${Versions.koin}")
    testImplementation("io.insert-koin:koin-test:${Versions.koin}")
}

fun DependencyHandler.koinAndroid() {
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")
    testImplementation("io.insert-koin:koin-android:${Versions.koin}")
}


fun DependencyHandler.coroutines() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    coroutinesTest()
}

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
    implementation("androidx.compose.ui:ui:${Versions.composeUi}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}")
}

fun DependencyHandler.composeDebug() {
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.composeUi}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.composeUi}")
}

fun DependencyHandler.material3() {
    implementation("androidx.compose.material3:material3:${Versions.material3}")
}

fun DependencyHandler.retrofit() {
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
}

fun DependencyHandler.moshi() {
    val version = "1.14.0"
    implementation("com.squareup.moshi:moshi:$version")
    implementation("com.squareup.moshi:moshi-adapters:$version")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$version")
}

fun DependencyHandler.coroutinesTest() {
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
}

fun DependencyHandler.junit() {
    testImplementation("junit:junit:${Versions.junit}")
}

fun DependencyHandler.mockk() {
    testImplementation("io.mockk:mockk:${Versions.mockk}")
}

fun DependencyHandler.androidTest() {
    androidTestImplementation("androidx.test.ext:junit:${Versions.junitExtensions}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoCore}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.composeUi}")
}

///////////////
// HELPERS:
///////////////

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
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
