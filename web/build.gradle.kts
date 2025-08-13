dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("io.springfox", "springfox-swagger2", libs.versions.swagger.get())
    implementation("io.springfox", "springfox-swagger-ui", libs.versions.swagger.get())

    compileOnly("org.projectlombok", "lombok", libs.versions.lombok.get())
    annotationProcessor("org.projectlombok", "lombok", libs.versions.lombok.get())
}
