plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.assigntwo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.assigntwo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val omdbApiKey: String? = System.getenv("OMDB_API_KEY") ?: project.findProperty("OMDB_API_KEY") as String?
        buildConfigField("String", "OMDB_API_KEY", "\"${omdbApiKey ?: ""}\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // ✅ Dùng Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true // ✅ Bật ViewBinding (Sửa lỗi findViewById)
        buildConfig = true // ✅ Fix lỗi BuildConfig
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.recyclerview:recyclerview:1.3.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.google.code.gson:gson:2.9.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.android.material:material:1.11.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin-stdlib")) {
            useVersion("1.8.22") // ✅ Fix lỗi duplicate class Kotlin
        }
    }
}
