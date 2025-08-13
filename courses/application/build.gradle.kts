dependencies {
    implementation(project(":common"))
    implementation(project(":security:security-config"))
    implementation(project(":courses:courses-integration-events"))
    implementation(project(":administration:administration-integration-events"))
    implementation(project(":course-enrollments:course-enrollments-integration-events"))
    implementation(project(":course-reviews:course-reviews-integration-events"))
    implementation(project(":users:users-integration-events"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    compileOnly("org.projectlombok:lombok:${libs.versions.lombok.get()}")
    annotationProcessor("org.projectlombok:lombok:${libs.versions.lombok.get()}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.mockito:mockito-junit-jupiter:${libs.versions.mockito.get()}")
    testImplementation("org.assertj:assertj-core:${libs.versions.assertj.get()}")

    testCompileOnly("org.projectlombok:lombok:${libs.versions.lombok.get()}")
    testAnnotationProcessor("org.projectlombok:lombok:${libs.versions.lombok.get()}")
}

tasks.test {
    useJUnitPlatform()
}

