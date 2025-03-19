plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    jacoco
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use TestNG framework, also requires calling test.useTestNG() below
    testImplementation(libs.testng)

    // This dependency is used by the application.
    implementation(libs.guava)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "fr.p10.miage.rps.model.App"
}

tasks.named<Test>("test") {
    // Use TestNG for unit tests.
    useTestNG(){
        suites("src/test/resources/testng.xml")
    }
    finalizedBy (tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn (tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}