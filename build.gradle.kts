import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    id("org.springframework.boot") version "2.2.1.RELEASE"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

val developmentOnly: Configuration by configurations.creating

configurations {
    runtimeClasspath {
        extendsFrom(configurations["developmentOnly"])
    }
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly("com.h2database:h2")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
