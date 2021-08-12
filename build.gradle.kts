plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.1stleg:jnativehook:2.1.0")
    implementation("ch.qos.logback:logback-classic:1.3.0-alpha7")
    implementation("net.kawinski.logging:nktrace:1.0.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
}

application {
    mainClass.set("net.kawinski.connectedworkspace.Main")
}

tasks.test {
    useJUnitPlatform()
}
