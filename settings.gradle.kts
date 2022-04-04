dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "MyStat"
include (":app")
include (":feature-screen-login")
include (":mylibrary:api")
include (":mylibrary:impl")
include (":mylibrary:test")
