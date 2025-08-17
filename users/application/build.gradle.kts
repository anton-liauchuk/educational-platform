dependencies {
    implementation(project(":common"))
    implementation(project(":security:security-config"))
    implementation(project(":users:users-integration-events"))

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("io.jsonwebtoken", "jjwt", libs.versions.jsonwebtoken.get())
    implementation("javax.xml.bind", "jaxb-api", libs.versions.jaxbApi.get())
    implementation("org.passay", "passay", libs.versions.passay.get())

    compileOnly("org.projectlombok", "lombok", libs.versions.lombok.get())
    annotationProcessor("org.projectlombok", "lombok", libs.versions.lombok.get())

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.junit.platform", "junit-platform-engine")
    testImplementation("org.junit.platform", "junit-platform-launcher")
    testImplementation("io.rest-assured", "rest-assured", libs.versions.restAssured.get())
    testImplementation("io.rest-assured", "json-path", libs.versions.restAssured.get())
    testImplementation("io.rest-assured", "xml-path", libs.versions.restAssured.get())
}

tasks.compileJava {
    options.compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
}

tasks.test {
    useJUnitPlatform()
}
