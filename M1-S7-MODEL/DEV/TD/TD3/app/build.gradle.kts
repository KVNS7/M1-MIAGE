plugins {
    // Application plugin for building Java CLI apps
    application
    jacoco
}

repositories {
    mavenCentral() // Maven Central for resolving dependencies
}

dependencies {
    // TestNG for testing
    testImplementation("org.testng:testng:7.4.0")
    // Guava for application logic
    implementation("com.google.guava:guava:31.1-jre")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21) // Java 21
    }
}

application {
    // Define the main class for the application
    mainClass.set("org.example.App")
}

// Test task configuration
tasks.named<Test>("test") {
    useTestNG() // Use TestNG framework for testing
    finalizedBy(tasks.jacocoTestReport) // Run JaCoCo test report after tests
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Make sure tests are executed before generating the report
    reports {
        xml.required.set(true) // Enable XML report
        html.required.set(true) // Enable HTML report
        html.outputLocation.set(file("$buildDir/reports/jacoco/test/html")) // HTML output location
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/xml")) // XML output location
    }
}
