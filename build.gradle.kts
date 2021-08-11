plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("com.google.guava:guava:29.0-jre")
}

application {
    mainClass.set("net.kawinski.connectedworkspace.Main")
}

tasks.test {
    useJUnitPlatform()
}
