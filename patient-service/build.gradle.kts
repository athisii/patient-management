import com.google.protobuf.gradle.id

plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version "0.9.4"

}

group = "com.athisii"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val grpcDepVersion = "1.71.0"
val protoBufJavaVersion = "4.30.1"
val swaggerVersion = "2.8.6"


dependencies {
    implementation("net.devh:grpc-spring-boot-starter:3.1.0.RELEASE")
    // gRPC dependencies for protobuf
    implementation("io.grpc:grpc-protobuf:${grpcDepVersion}")
    implementation("io.grpc:grpc-stub:${grpcDepVersion}")
    implementation("io.grpc:grpc-netty-shaded:${grpcDepVersion}")
    // protobuf plugin for code generation
    implementation("com.google.protobuf:protobuf-java:${protoBufJavaVersion}")

    implementation("org.springframework.kafka:spring-kafka")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${swaggerVersion}")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protoBufJavaVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcDepVersion}"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

