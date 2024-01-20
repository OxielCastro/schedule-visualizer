plugins {
    id("java")
    id("application")
    id("org.javamodularity.moduleplugin") version "1.8.9" apply false
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "2.24.1"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openjfx:javafx-controls:19")
    implementation("org.openjfx:javafx-fxml:19")
    implementation("org.openjfx:javafx-web:19")
    implementation("org.controlsfx:controlsfx:11.1.1")
    implementation("com.dlsc.formsfx:formsfx-core:11.5.0")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0")
    compileOnly("org.jetbrains:annotations:13.0")
    testImplementation("org.mockito:mockito-core:4.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.testfx:testfx-core:4.0.17")
    testImplementation("org.testfx:testfx-junit5:4.0.17")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

javafx {
    version = "17"
    setPlatform("mac")
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainModule.set("edu.hanover.schedulevisualizer")
    mainClass.set("edu.hanover.schedulevisualizer.HelloApplication")
}
