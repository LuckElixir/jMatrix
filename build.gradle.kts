plugins {
    id("java")
    id("maven-publish")
    id("java-library")
}

group = "personal.luckelixir.jmatrix"
version = "1.0"

repositories {
    mavenCentral()

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/luckelixir/jMatrix")
            credentials {
                username = project.findProperty("jMatrix.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("jMatrix.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("jMatrix") {
            from(components["java"])
            artifactId = "jmatrix-1.0"
        }

    }
}
