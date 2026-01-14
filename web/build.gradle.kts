dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("org.springdoc", "springdoc-openapi-starter-webmvc-ui", libs.versions.springDoc.get())
}
