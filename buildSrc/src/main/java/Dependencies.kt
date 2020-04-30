object Versions {
    const val kotlin = "1.3.61"

    const val appcompat = "1.1.0"
    const val core = "1.2.0"

    const val retrofit = "2.6.0"
    const val loggingInterceptor = "3.12.0"
    const val chuck = "1.1.0"

    const val timber = "4.7.1"
}

object Kotlin {
    const val kotlinStandard = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
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

object ThirdPartyLibrary {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}
