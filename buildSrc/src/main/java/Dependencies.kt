object Versions {
    const val kotlin = "1.3.70"

    const val appcompat = "1.1.0"
    const val core = "1.2.0"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.0-alpha06"
    const val legacy = "1.0.0"
    const val constraint = "1.1.3"

    const val material = "1.1.0"
    const val playService = "17.0.0"
    const val maps = "1.2.1"

    const val retrofit = "2.6.0"
    const val loggingInterceptor = "3.12.0"
    const val chuck = "1.1.0"
    const val glide = "4.10.0"

    const val timber = "4.7.1"
    const val androidChart = "v3.1.0"

    const val koin = "2.1.5"

    const val junit = "4.12"
    const val runner = "1.2.0"
    const val expresso = "3.2.0"
    const val mockitoCore = "3.0.0"
    const val mockitoInline = "3.0.0"
    const val coroutinesTest = "1.3.3"
    const val mockk = "1.9.3"
    const val spekDsl = "2.0.9"
    const val spekRunner = "2.0.9"

}

object Kotlin {
    const val kotlinStandard = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val playServices = "com.google.android.gms:play-services-maps:${Versions.playService}"
    const val maps = "com.google.maps.android:android-maps-utils:${Versions.maps}"
}

object Square {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.loggingInterceptor}"
}

object Chuck {
    const val chuck = "com.readystatesoftware.chuck:library:${Versions.chuck}"
    const val chuckLibraryNoOp = "com.readystatesoftware.chuck:library-no-op:${Versions.chuck}"
}

object Koin {
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object ThirdPartyLibrary {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val androidChart = "com.github.PhilJay:MPAndroidChart:${Versions.androidChart}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompile = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val expresso = "androidx.test.espresso:espresso-core:${Versions.expresso}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val spekDsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spekDsl}"
    const val spekRunner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spekRunner}"
}
