dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Quote of the day"
include(":app")
include(":data")
include(":common")
include(":domain")
include(":feature:home")
include(":feature:history")
include(":navigation")
include(":feature:favorite")
include(":feature:settings")
