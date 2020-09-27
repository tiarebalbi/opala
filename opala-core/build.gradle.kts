plugins {
    id("org.jetbrains.kotlin.jvm")
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    // Kotlin
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Libs
    val konfigVersion: String by project
    val kotlinLogging: String by project

    implementation("io.github.microutils:kotlin-logging:$kotlinLogging")
    implementation("com.natpryce:konfig:$konfigVersion")

    // Test
    val junit5Version:String by project
    val assertJVersion: String by project

    testImplementation(platform("org.junit:junit-bom:$junit5Version"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
}