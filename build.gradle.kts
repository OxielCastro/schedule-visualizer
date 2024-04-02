plugins {
    id("application")
    id("co.uzzu.dotenv.gradle") version "4.0.0"
//    id("org.javamodularity.moduleplugin") version "1.8.9" apply false
    id("org.openjfx.javafxplugin") version "0.1.0"
//    id("org.beryx.jlink") version "2.24.1"
    pmd
    jacoco
}

version = env.fetch("VERSION")

val vendors = mapOf(
    "ADOPTIUM" to JvmVendorSpec.ADOPTIUM,
    "AMAZON" to JvmVendorSpec.AMAZON,
    "APPLE" to JvmVendorSpec.APPLE,
    "AZUL" to JvmVendorSpec.AZUL,
    "MICROSOFT" to JvmVendorSpec.MICROSOFT,
    "ORACLE" to JvmVendorSpec.ORACLE,
    "IBM" to JvmVendorSpec.IBM,
)

java {
    toolchain {
        val javaVersion = env.fetch("JAVA_VERSION", "17")
        val javaVendor = env.fetch("JAVA_VENDOR", "")
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
        vendor.set(vendors.getOrDefault(javaVendor, JvmVendorSpec.AMAZON))
    }
}

repositories {
    mavenCentral()
}

val markdownDoclet by configurations.creating

dependencies {
    markdownDoclet("org.jdrupes.mdoclet:doclet:4.1.0")
    implementation("org.openjfx:javafx-controls:21")
    implementation("org.openjfx:javafx-fxml:21")
    implementation("org.openjfx:javafx-web:21")
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
    // Database stuff
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    runtimeOnly("org.hibernate.orm:hibernate-core:6.4.3.Final")
    testRuntimeOnly("com.h2database:h2:2.2.224")
    // JSON, YAML
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    testImplementation(platform("io.cucumber:cucumber-bom:7.16.1"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("org.junit.platform:junit-platform-suite")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

javafx {
    version = "21.0.2"
//    setPlatform("mac")
    modules("javafx.controls", "javafx.fxml")
}

//jlink {
//    launcher {
//        name = "edu.hanover.schedulevisualizer"
//    }
//}

application {
    mainModule.set("edu.hanover.schedulevisualizer")
    mainClass.set("edu.hanover.schedulevisualizer.HelloApplication")
    applicationDefaultJvmArgs += listOf("-Djavafx.verbose=true", "-Dprism.verbose=true")
}

//java {
//    modularity.inferModulePath.set(true)
//}

pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = false
    isIgnoreFailures = true
    ruleSetFiles(files("config/pmd.xml"))
}

tasks.test {
    finalizedBy(tasks.named("jacocoTestReport")) // report is always generated after tests run
}
tasks.named<Task>("jacocoTestReport") {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.build {
    dependsOn(tasks.javadoc)
}

tasks.javadoc {
    options.docletpath = markdownDoclet.files.toList()
    options.doclet = "org.jdrupes.mdoclet.MDoclet"
    options.quiet()
    (options as CoreJavadocOptions).addStringOption("Xdoclint:-html")

    options.jFlags = listOf(
        "--add-exports=jdk.compiler/com.sun.tools.doclint=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED",
        "--add-exports=jdk.javadoc/jdk.javadoc.internal.tool=ALL-UNNAMED",
        "--add-exports=jdk.javadoc/jdk.javadoc.internal.doclets.toolkit=ALL-UNNAMED",
        "--add-opens=jdk.javadoc/jdk.javadoc.internal.doclets.toolkit.resources.releases=ALL-UNNAMED")
}