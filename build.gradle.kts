plugins {
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation("io.netty:netty-all:4.1.66.Final")
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

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "net.kawinski.connectedworkspace.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}
