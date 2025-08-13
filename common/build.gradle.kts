dependencies {
    api("org.axonframework", "axon-spring-boot-starter", libs.versions.axon.get()) {
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }

    runtimeOnly("com.h2database", "h2")
}