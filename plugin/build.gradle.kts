/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details take a look at the Writing Custom Plugins chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8/userguide/custom_plugins.html
 */

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.20"

    `maven-publish`
}

group = "com.github.jomof"
version = "0.3"

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

gradlePlugin {
    // Define the plugin

    val myPlugin by plugins.creating {
        id = "prefab"
        implementationClass = "com.github.jomof.prefab.plugin.PrefabPlugin"
    }
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {
}

gradlePlugin.testSourceSets(functionalTestSourceSet)
configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
}

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/jomof/prefab-plugin")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    //afterEvaluate {

        publications{
 //           val plugin = maybeCreate<MavenPublication>("pluginMaven")
//            println(plugin)
//            plugin.groupId = "com.github.jomof.prefab"
//            plugin.artifactId = "xxx-yyy"
//            plugin.version = "0.2"

//            create<MavenPublication>("pluginMaven") {
//                groupId = "com.github.jomof"
//                artifactId = "prefab-plugin"
//                version = "0.1"
//            }
//        }
        }
 //   }
//    publications {
//        create<MavenPublication>("gpr") {
//            groupId = "com.github.jomof"
//            artifactId = "prefab-plugin"
//            version = "0.1"
//
//            from(components["java"])
//        }
//    }
}
