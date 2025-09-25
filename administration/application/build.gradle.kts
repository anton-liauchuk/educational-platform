dependencies {
    implementation(project(":common"))
    implementation(project(":security:security-config"))
    implementation(project(":courses:courses-integration-events"))
    implementation(project(":administration:administration-integration-events"))

    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("jakarta.inject", "jakarta.inject-api")

    compileOnly("org.projectlombok", "lombok", libs.versions.lombok.get())
    annotationProcessor("org.projectlombok", "lombok", libs.versions.lombok.get())

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.security", "spring-security-test")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.junit.platform", "junit-platform-engine")
    testImplementation("org.junit.platform", "junit-platform-launcher")
    testImplementation("org.mockito", "mockito-junit-jupiter", libs.versions.mockito.get())
    testImplementation("org.assertj", "assertj-core", libs.versions.assertj.get())
}

tasks.test {
    useJUnitPlatform()
}
