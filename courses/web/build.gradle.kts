dependencies {
    implementation(project(":common"))
    implementation(project(":web"))
    implementation(project(":security:security-config"))
    implementation(project(":courses:courses-application"))

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")

    testImplementation(project(":users:users-web"))
    testImplementation(project(":security:security-test"))
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-webmvc-test")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.junit.platform", "junit-platform-engine")
    testImplementation("org.junit.platform", "junit-platform-launcher")
    testImplementation("org.mockito", "mockito-junit-jupiter", libs.versions.mockito.get())
    testImplementation("org.assertj", "assertj-core", libs.versions.assertj.get())
    testImplementation("io.rest-assured", "rest-assured", libs.versions.restAssured.get())
    testImplementation("io.rest-assured", "json-path", libs.versions.restAssured.get())
    testImplementation("io.rest-assured", "xml-path", libs.versions.restAssured.get())
}

tasks.test {
    useJUnitPlatform()
}

