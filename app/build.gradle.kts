plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.gms.google-services")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.mystat"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding =  true
    }
}

dependencies {

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.1.0")
    implementation ("androidx.core:core-ktx:1.3.2")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("com.google.android.material:material:1.2.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("com.google.firebase:firebase-database:20.0.2")
    implementation ("com.google.firebase:firebase-database-ktx:20.0.2")
    implementation ("com.google.firebase:firebase-auth-ktx:21.0.1")
    implementation ("com.google.android.gms:play-services-auth:19.2.0")


    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.fragment:fragment-ktx:1.4.1")
    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.1")


    //Coroutines

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:2.4.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:2.4.1")

    //RoomDao
    implementation ("androidx.room:room-runtime:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
    implementation ("androidx.room:room-ktx:2.4.2")

    //Logs
    implementation ("com.jakewharton.timber:timber:4.7.1")

    //View binding delegate
    implementation ("com.kirich1409.viewbindingpropertydelegate:vbpd-noreflection:1.3.0")

//    implementation platform("com.google.firebase:firebase-bom:28.4.2")
    implementation ("com.google.firebase:firebase-analytics-ktx")
//    implementation "com.google.firebase: firebase-database"

    implementation ("androidx.activity:activity-ktx:1.4.0")
    implementation ("androidx.fragment:fragment-ktx:1.3.6")

    //Picasso
    implementation ("com.squareup.picasso:picasso:2.71828")
}