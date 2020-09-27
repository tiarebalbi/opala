package com.tiarebalbi.opala.compilers.kotlin

import com.tiarebalbi.opala.common.FileUtils
import com.tiarebalbi.opala.compilers.Compiler
import com.tiarebalbi.opala.exceptions.CompilationError
import com.tiarebalbi.opala.models.compiler.Configuration
import com.tiarebalbi.opala.models.structure.Class
import com.tiarebalbi.opala.models.structure.File
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

class KotlinCompilerImpl(private val configuration: Configuration) : Compiler {

    override fun process(specification: Set<File>): Set<java.io.File> {
        // 1. Generated distribution folder
        if (FileUtils.notExists(configuration.distributionFolder)) {
            FileUtils.createDirectories(configuration.distributionFolder)
        }

        // 2. Build Main Class
        val generatedFiles = mutableSetOf<java.io.File>()
        specification.forEach {
            when (it) {
                is Class -> {
                    val classFile = ClassCompiler(it).compile(configuration)
                    val testFile = TestClassCompiler(it).compile(configuration)

                    generatedFiles += classFile
                    generatedFiles += testFile
                }
                else -> {
                    throw CompilationError("No Compiler available for type ${it::javaClass}")
                }
            }
        }

        // 3. Clean not specified classes
        val existentFiles = Files.walk(Path.of(configuration.distributionFolder), FileVisitOption.FOLLOW_LINKS)
            .map { it.toString() }
            .filter { it.endsWith(".kt") }
            .collect(Collectors.toSet())

        val generatedFilePath = generatedFiles.map { file -> file.toString() }.toSet()

        existentFiles.removeAll(generatedFilePath)
        existentFiles.forEach { FileUtils.deleteIfExists(it) }

        // 4. Details
        return generatedFiles
    }
}