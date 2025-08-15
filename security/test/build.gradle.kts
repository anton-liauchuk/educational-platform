dependencies {
    implementation(project(":users:users-web"))
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("io.rest-assured", "rest-assured", libs.versions.restAssured.get())
    implementation("io.rest-assured", "json-path", libs.versions.restAssured.get())
    implementation("io.rest-assured", "xml-path", libs.versions.restAssured.get())
}

