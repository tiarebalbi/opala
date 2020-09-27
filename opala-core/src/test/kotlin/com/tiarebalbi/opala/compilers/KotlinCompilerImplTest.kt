package com.tiarebalbi.opala.compilers

import com.natpryce.konfig.ConfigurationProperties
import com.tiarebalbi.opala.common.FileUtils
import com.tiarebalbi.opala.compilers.kotlin.KotlinCompilerImpl
import com.tiarebalbi.opala.models.compiler.Configuration
import com.tiarebalbi.opala.models.structure.Class
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class KotlinCompilerImplTest {
    private val configuration = Configuration.from(ConfigurationProperties.fromResource("configuration.properties"))

    private val clazz = setOf(Class("Demo", "com.tiarebalbi.demo"))

    @Test
    internal fun `should be able to compile class`() {
        val compiler = KotlinCompilerImpl(configuration)

        val summary = compiler.run(clazz)

        assertTrue(summary.duration.toMillis() > 0)
        assertThat(summary.compiledFiles).hasSize(2)
    }

    @Test
    internal fun `should create the class and remove files that are not listed in the specification`() {
        FileUtils.createDirectories("${configuration.distributionFolder}/src/test/kotlin/com/tiarebalbi/demo")
        FileUtils.createFile(
            "${configuration.distributionFolder}/src/test/kotlin/com/tiarebalbi/demo/Fake.kt",
            "Sample"
        )

        val compiler = KotlinCompilerImpl(configuration)

        val summary = compiler.run(clazz)

        assertTrue(summary.duration.toMillis() > 0)
        assertThat(summary.compiledFiles).hasSize(2)
    }

    @AfterEach
    internal fun tearDown() {
        FileUtils.recursiveDelete(configuration.distributionFolder)
    }
}
