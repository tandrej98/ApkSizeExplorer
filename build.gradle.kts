plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.spotless)
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.hilt) apply false
}

subprojects {
  apply(plugin = "com.diffplug.spotless")
  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**/*.kt")
      targetExclude("bin/**/*.kt")
      ktlint()
        .editorConfigOverride(
          mapOf(
            "android" to "true",
            "indent_style" to "space",
            "indent_size" to "2",
            "continuation_indent_size" to "2",
            "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
            "ktlint_standard_filename" to "disabled",
            "ktlint_standard_if-else-bracing" to "disabled",
          ),
        )
    }
    kotlinGradle {
      target("*.gradle.kts")
      ktlint()
    }
  }

  afterEvaluate { tasks.named("build") { dependsOn("spotlessApply") } }
}
