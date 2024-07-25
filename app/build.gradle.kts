import dev.yaghm.plugin.internal.core.dsl.bash.Interpreter
import dev.yaghm.plugin.internal.core.dsl.githook.action
import dev.yaghm.plugin.internal.core.dsl.githook.configure
import dev.yaghm.plugin.internal.core.dsl.githook.doFirst
import dev.yaghm.plugin.internal.core.dsl.githook.doLast
import dev.yaghm.plugin.internal.core.dsl.githook.echo
import dev.yaghm.plugin.internal.core.dsl.githook.gradle
import dev.yaghm.plugin.internal.core.dsl.githook.onFile
import dev.yaghm.plugin.internal.core.dsl.githook.preCommit
import dev.yaghm.plugin.internal.core.dsl.githook.useShebang

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("io.github.andrew-malitchuk.yaghm") version "0.0.1-a.2"
}

android {
    namespace = "dev.yapp.io"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.yapp.io"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

yaghm {
    gitHook {
        preCommit {
            doFirst {
                echo("hello world")
            }
            action {
                "echo \"main action\""
            }
            doLast {
                gradle("detekt")
            }
            useShebang {
                Interpreter.BASH
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(project(":2048"))

    // Koin core features
    implementation("io.insert-koin:koin-core:3.5.0")
    implementation("io.insert-koin:koin-android:3.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.2")
}