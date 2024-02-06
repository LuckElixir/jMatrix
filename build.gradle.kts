plugins {
    id("java")
    id("maven-publish")
    id("java-library")
}

group = "personal.luckelixir.jmatrix"
version = "1.0-SNAPSHOT"

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
    publications {
        create<MavenPublication>("jMatrix") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "jMatrixRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}