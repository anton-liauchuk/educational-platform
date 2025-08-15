dependencies {
    implementation(project(":common"))
    implementation("org.springframework", "spring-context")

    compileOnly("org.projectlombok", "lombok", libs.versions.lombok.get())
    annotationProcessor("org.projectlombok", "lombok", libs.versions.lombok.get())
}
