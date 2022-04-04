plugins {
    `android-lib-convention`
    `android-lib-compose-convention`
    kotlin("kapt")
}

dependencies {


    api(project(":feature-screen-settings:api"))

    implementation(Dependencies.AndroidX.navigationCompose)

    implementation(Dependencies.Dagger.daggerImpl)
    kapt(Dependencies.Dagger.daggerKapt)
}
