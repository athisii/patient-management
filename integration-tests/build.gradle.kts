plugins {
    id("java")
}

group = "com.athisii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val restAssuredVersion = "5.5.1"

dependencies {
    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}