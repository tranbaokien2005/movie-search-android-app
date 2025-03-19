plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.assigntwo" // ✅ Fix lỗi thiếu namespace
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.assigntwo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ✅ Đảm bảo BuildConfig hoạt động
        val omdbApiKey: String? = System.getenv("OMDB_API_KEY") ?: project.findProperty("OMDB_API_KEY") as String?
        buildConfigField("String", "OMDB_API_KEY", "\"${omdbApiKey ?: ""}\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // ✅ Dùng Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true // ✅ Fix lỗi BuildConfig
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1") // ✅ Fix lỗi thiếu AppCompatActivity
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ✅ Thêm thư viện RecyclerView, LinearLayoutManager
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // ✅ Retrofit - Gọi API từ OMDB
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ Gson - Chuyển đổi JSON thành Object
    implementation("com.google.code.gson:gson:2.9.0")

    // ✅ Glide - Load ảnh Poster từ URL
    implementation("com.github.bumptech.glide:glide:4.15.1")
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin-stdlib")) {
            useVersion("1.8.22") // ✅ Fix lỗi duplicate class Kotlin
        }
    }
}
