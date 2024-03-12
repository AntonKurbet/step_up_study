plugins {
    id("java")
    id("org.springframework.boot") version "3.1.9"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "inno.tech"
version = "1.0-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("org.liquibase:liquibase-core:4.26.0")
        compileOnly("org.projectlombok:lombok")
        implementation("org.postgresql:postgresql:42.6.0")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

tasks.test {
    useJUnitPlatform()
}